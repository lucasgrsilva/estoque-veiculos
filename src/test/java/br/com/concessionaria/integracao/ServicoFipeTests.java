package br.com.concessionaria.integracao;

import br.com.concessionaria.domain.dto.DetalhesModeloApiResponse;
import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.dto.ModeloAnosApiResponse;
import br.com.concessionaria.domain.dto.ModeloApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.exception.RequisicaoInvalidaException;
import br.com.concessionaria.service.ServicoFipe;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServicoFipeTests {

    private ServicoFipe servicoFipe;

    @BeforeEach
    public void setup() {
        //Arrange
        this.servicoFipe = new ServicoFipe();
    }

    @ParameterizedTest
    @EnumSource(value = TipoVeiculo.class)
    public void quandoBuscarMarcasFipe_PassandoTipoVeiculo_DeveRetornarMarcasCadastradas(TipoVeiculo tipoVeiculo)
            throws Exception {
        //Act
        List<MarcaApiResponse> marcas = servicoFipe.buscarMarcasFipe(tipoVeiculo);

        //Assert
        assertFalse(marcas.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(value = {"carros, 13", "motos, 80"})
    public void quandoBuscarModelosPorMarca_PassandoTipoDeVeiculoEcodigoDeMarcaValidos_DeveRetornarModelosDaMarca(
            TipoVeiculo tipoVeiculo, String codMarca) throws Exception {
        //Act
        ModeloApiResponse anosEmodelos = servicoFipe.buscarModelosPorMarca(tipoVeiculo, codMarca);

        List<ModeloAnosApiResponse> anos = Arrays.stream(anosEmodelos.anos).toList();
        List<ModeloAnosApiResponse> modelos = Arrays.stream(anosEmodelos.modelos).toList();
        //Assert
        assertFalse(anos.isEmpty() && modelos.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(value = {"carros, 8888", "motos, 8888"})
    public void quandoBuscarModelosPorMarca_PassandoCodigoDeMarcaInvalido_DeveLancarExcecao(
            TipoVeiculo tipoVeiculo, String codMarca) throws Exception {

        var excecaoLancada = assertThrows(RequisicaoInvalidaException.class,
                () -> servicoFipe.buscarModelosPorMarca(tipoVeiculo, codMarca));

        assertEquals("Dados de marca ou veículo inválidos", excecaoLancada.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"carros, 59, 5940, 2014-3", "motos, 80, 10610, 2023-1"})
    public void quandoBuscarModelo_PassandoTipoVeiculoEcodigoMarcaEcodigoVeiculoEanoValidos_DeveRetornarModelo(
            TipoVeiculo tipoVeiculo, String codMarca, String codModelo, String ano) throws Exception {
        //Act
        DetalhesModeloApiResponse modelo = servicoFipe.buscarModelo(tipoVeiculo, codMarca, codModelo, ano);

        //Assert
        assertNotNull(modelo);
    }

    @ParameterizedTest
    @CsvSource(value = {"carros, 8888, 5940, 2014-3", "motos, 8888, 10610, 2023-1", "carros, 59, 88888, 2014-3",
            "motos, 80, 88888, 2023-1", "carros, 80, 5940, 00000", "motos, 80, 10610, 00000"})
    public void quandoBuscarModelo_PassandoInformacoesNaoExistentesNoServidor_DeveLancarExcecao(
            TipoVeiculo tipoVeiculo, String codMarca, String codModelo, String ano) throws Exception {

        RequisicaoInvalidaException excecaoLancada = assertThrows(RequisicaoInvalidaException.class,
                () -> servicoFipe.buscarModelo(tipoVeiculo, codMarca, codModelo, ano));

        assertEquals("Dados de marca ou veículo inválidos", excecaoLancada.getMessage());
    }

}
