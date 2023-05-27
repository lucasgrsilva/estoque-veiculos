package br.com.concessionaria.integracao;

import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.service.ServicoFipe;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

public class ServicoFipeTests {

    private ServicoFipe servicoFipe;

    @Before
    public void setup() {
        ServicoFipe servicoFipe = new ServicoFipe();
    }

    @Test
    public void quandoChamadoBuscarFipe_PassandoTipoVeiculo_DeveRetornarMarcasCadastradas() throws Exception {
        //Arrrange
        TipoVeiculo carros = TipoVeiculo.carros;

        //Act
        MarcaApiResponse[] marcas = servicoFipe.buscarFipe(carros);

        //Assert
        Assert.notEmpty(marcas);
    }
}
