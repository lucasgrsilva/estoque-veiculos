package br.com.concessionaria.integracao;

import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.dto.ModeloAnosApiResponse;
import br.com.concessionaria.domain.dto.ModeloApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.service.ServicoFipe;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
    @CsvSource(value = {"carros,13", "motos, 80"})
    public void quandoBuscarModelosPorMarca_PassandoTipoDeVeiculoEcodigoDeMarcaValidos_DeveRetornarModelosDaMarca(
            TipoVeiculo tipoVeiculo, String codMarca) throws Exception {
        //Act
        ModeloApiResponse anosEmodelos = servicoFipe.buscarModelosPorMarca(tipoVeiculo, codMarca);

        List<ModeloAnosApiResponse> anos = Arrays.stream(anosEmodelos.anos).toList();
        List<ModeloAnosApiResponse> modelos = Arrays.stream(anosEmodelos.modelos).toList();
        //Assert
        assertFalse(anos.isEmpty() && modelos.isEmpty());
    }
}
