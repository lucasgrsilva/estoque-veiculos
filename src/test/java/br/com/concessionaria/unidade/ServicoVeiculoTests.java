package br.com.concessionaria.unidade;

import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.exception.VeiculoInvalidoException;
import br.com.concessionaria.repository.RepositorioVeiculos;
import br.com.concessionaria.service.ServicoVeiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServicoVeiculoTests
{
    @Mock
    RepositorioVeiculos repositorioVeiculos;

    @InjectMocks
    ServicoVeiculo servicoVeiculo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void quandoChamadoGetVeiculos_DeveRetornarVeiculosSalvos() {
        // Arrange
        Carro carro = new Carro(1, 123, "abc1234", "Cruze", 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet, 2020,
                LocalDate.parse("2020-06-02"), BigDecimal.valueOf(68000), BigDecimal.valueOf(85000));

        when(repositorioVeiculos.getAll()).thenReturn(List.of(carro));

        //Act
        List<Veiculo> veiculos = servicoVeiculo.getVeiculos();

        //Assert
        assertEquals(carro, veiculos.get(0));
    }

    @Test
    public void quandoTentarAdicionarCarro_PassandoCarroComChassiEplacaUnicos_DeveSalvarEretornarVeiculo() {
        //Arrange
        Carro novoCarro = new Carro(0, 123, "abc1234", "Cruze", 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet, 2020,
                LocalDate.parse("2020-06-02"), BigDecimal.valueOf(68000), BigDecimal.valueOf(85000));

        int proximoId = 1;

        when(repositorioVeiculos.getProximoId()).thenReturn(proximoId);

        //Act
        Carro carroSalvo = servicoVeiculo.adicionarCarro(novoCarro);

        //Assert
        verify(repositorioVeiculos).adicionarVeiculo(novoCarro);
        assertEquals(carroSalvo, novoCarro);
    }

    @Test
    public void quandoTentarAdicionarCarro_PassandoCarroComChassiDuplicado_DeveLancarExcecao() {
        Carro carroComChassiDuplicado = new Carro();
        carroComChassiDuplicado.setChassi(123);

        when(repositorioVeiculos.getVeiculoPorChassi(carroComChassiDuplicado.getChassi()))
                .thenReturn(Optional.of(new Carro()));


        var excecaoLancada = assertThrows(VeiculoInvalidoException.class, () ->
                servicoVeiculo.adicionarCarro(carroComChassiDuplicado));

        assertEquals("Chassi duplicado", excecaoLancada.getMessage());
    }

    @Test
    public void quandoTentarAdicionarCarro_PassandoCarroComPlacaDuplicada_DeveLancarExcecao() {
        Carro carroComPlacaDuplicada = new Carro();
        carroComPlacaDuplicada.setChassi(123);
        carroComPlacaDuplicada.setPlaca("adc1235");

        when(repositorioVeiculos.getVeiculoPorChassi(carroComPlacaDuplicada.getChassi()))
                .thenReturn(Optional.empty());
        when(repositorioVeiculos.getVeiculoPorPlaca(carroComPlacaDuplicada.getPlaca()))
                .thenReturn(Optional.of(new Carro()));

        var excecaoLancada = assertThrows(VeiculoInvalidoException.class, () ->
                servicoVeiculo.adicionarCarro(carroComPlacaDuplicada));

        assertEquals("Placa duplicada", excecaoLancada.getMessage());
    }
}
