package br.com.concessionaria.integracao;

import br.com.concessionaria.ConcessionariaApplication;
import br.com.concessionaria.domain.dto.RequisicaoNovaMoto;
import br.com.concessionaria.domain.dto.RequisicaoNovoCarro;
import br.com.concessionaria.domain.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ConcessionariaApplication.class)
public class EndpointsVeiculoIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate;

    private String applicationUrl;

    @Before
    public void setUp() {
        this.applicationUrl = "http://localhost:" + port + "/api/veiculos";
        restTemplate = new TestRestTemplate();
    }


    @Test
    public void quandoAcessadoVeiculoPorChassi_PassandoChassiExistente_DeveRetornarVeiculo()
            throws JsonProcessingException {
        int chassiExistente = 123;

        Veiculo response = restTemplate.getForObject(applicationUrl + "/chassi/" + chassiExistente, Veiculo.class);


        assertNotNull(response);
        assertEquals(chassiExistente, response.getChassi());
    }

    @Test
    public void quandoAcessadoVeiculoPorChassi_PassandoChassiNaoExistente_DeveRetornarExcecao() {
        int chassiNaoExistente = 0;

        String response = restTemplate.getForObject(applicationUrl + "/chassi/" + chassiNaoExistente, String.class);

        assertTrue(response.contains("\"status\":404"));
        assertTrue(response.contains("\"message\":\"Veículo não encontrado\""));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void quandoAcessadoCarro_AposAdicionado_DeveRetornarCarro() {
        RequisicaoNovoCarro novoCarro = new RequisicaoNovoCarro(78955, "pur12234", "Celta", 2000,
                LocalDate.parse("2022-06-02"), BigDecimal.valueOf(20000), BigDecimal.valueOf(15000), 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet);

        var postResponse = restTemplate.postForEntity(applicationUrl + "/carros", novoCarro, Carro.class);

        Carro getResponse = restTemplate.getForObject(applicationUrl + "/chassi/" + novoCarro.getChassi(), Carro.class);

        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        assertEquals(novoCarro.getChassi(), getResponse.getChassi());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void quandoTentarAdicionarCarro_PassandoChassiDuplicado_DeveRetornarExcecao() {
        RequisicaoNovoCarro novoCarro = new RequisicaoNovoCarro(123, "pur12234", "Celta", 2000,
                LocalDate.parse("2022-06-02"), BigDecimal.valueOf(20000), BigDecimal.valueOf(15000), 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet);

        Veiculo[] veiculosAntesPost = restTemplate.getForObject(applicationUrl, Veiculo[].class);
        var postResponse = restTemplate.postForEntity(applicationUrl + "/carros", novoCarro, String.class);
        Veiculo[] veiculosDepoisPost = restTemplate.getForObject(applicationUrl, Veiculo[].class);

        assertEquals(veiculosAntesPost.length, veiculosDepoisPost.length);
        assertEquals(HttpStatus.BAD_REQUEST, postResponse.getStatusCode());
        assertTrue(postResponse.getBody().contains("\"message\":\"Chassi duplicado\""));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void quandoAcessadoVeiculo_AposDeletado_DeveRetornarExcecao() {
        int chassi = 321;

        Veiculo veiculo = restTemplate.getForObject(applicationUrl + "/chassi/" + chassi, Veiculo.class);

        restTemplate.delete(applicationUrl + "/" + veiculo.getId());

        String response = restTemplate.getForObject(applicationUrl + "/chassi/" + chassi, String.class);

        assertTrue(response.contains("\"status\":404"));
        assertTrue(response.contains("\"message\":\"Veículo não encontrado\""));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void quandoBuscaVeiculosPorAnoDeFabricacao_PassandoAnoMinEanoMax_DeveRetornarVeiculosCorrespondentes() {
        RequisicaoNovoCarro carroAnoDesejado = new RequisicaoNovoCarro(189, "pur12234", "Celta", 2000,
                LocalDate.parse("2022-06-02"), BigDecimal.valueOf(20000), BigDecimal.valueOf(15000), 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet);

        RequisicaoNovaMoto motoAnoDesejado = new RequisicaoNovaMoto(564, "lop8566", "PCX", 2001,
                LocalDate.parse("2021-05-08"), BigDecimal.valueOf(9000), BigDecimal.valueOf(10000), 149,
                12, MarcasMoto.Honda);

        RequisicaoNovaMoto motoAnoIndesejado = new RequisicaoNovaMoto(568, "nop8566", "XRE 300", 2002,
                LocalDate.parse("2021-05-08"), BigDecimal.valueOf(9000), BigDecimal.valueOf(10000), 149,
                12, MarcasMoto.Honda);

        restTemplate.postForEntity(applicationUrl + "/motos", motoAnoDesejado, String.class);
        restTemplate.postForEntity(applicationUrl + "/motos", motoAnoIndesejado, String.class);
        restTemplate.postForEntity(applicationUrl + "/carros", carroAnoDesejado, String.class);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(applicationUrl + "/ano")
                .queryParam("anoMax", motoAnoDesejado.getAnoFabricacao())
                .queryParam("anoMin", 1999)
                .encode()
                .toUriString();

        Veiculo[] veiculosRetornados = restTemplate.getForObject(urlTemplate, Veiculo[].class);

        assertEquals(2, veiculosRetornados.length);
        assertEquals(motoAnoDesejado.getAnoFabricacao(), veiculosRetornados[0].getAnoFabricacao());
        assertEquals(carroAnoDesejado.getAnoFabricacao(), veiculosRetornados[1].getAnoFabricacao());
    }
}
