package br.com.concessionaria.unidade;

import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.repository.RepositorioVendas;
import br.com.concessionaria.service.*;
import br.com.concessionaria.domain.dto.RequisicaoNovaVenda;
import br.com.concessionaria.exception.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServicoVendaTest {

    @Mock
    private RepositorioVendas repositorioVendas;

    @Mock
    private ServicoVeiculo servicoVeiculo;

    @Mock
    private ServicoCliente servicoCliente;

    @InjectMocks
    private ServicoVenda servicoVenda;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdicionarVenda() {
        RequisicaoNovaVenda requisicaoNovaVenda = new RequisicaoNovaVenda(LocalDate.now(), BigDecimal.valueOf(10000), 12, 10,
                MetodoDePagamento.CARTAO_DE_DEBITO, 1);

        int proximoId = 4;

        Cliente cliente = new Cliente(10, "João", "123456789", null, null);
        Carro carro = new Carro(12, 123, "abc1234", "Cruze", 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet, 2020,
                LocalDate.parse("2020-06-02"), BigDecimal.valueOf(68000), BigDecimal.valueOf(85000));

        when(servicoCliente.buscarPorId(10)).thenReturn(cliente);
        when(servicoVeiculo.buscarVeiculoPorId(12)).thenReturn(carro);

        when(repositorioVendas.getProximoId()).thenReturn(proximoId);

        doNothing().when(repositorioVendas).adicionarVenda(any(Venda.class));

        Venda vendaAdicionada = servicoVenda.adicionarVenda(requisicaoNovaVenda);

        assertEquals(proximoId, vendaAdicionada.getId());
        assertEquals(requisicaoNovaVenda.getDataDaVenda(), vendaAdicionada.getDataDaCompra());
        assertEquals(requisicaoNovaVenda.getPrecoDeVenda(), vendaAdicionada.getPrecoDeVenda());
        assertEquals(requisicaoNovaVenda.getIdVeiculo(), vendaAdicionada.getVeiculoVendido().getId());
        assertEquals(requisicaoNovaVenda.getIdCliente(), vendaAdicionada.getClienteVenda().getId());
        assertEquals(requisicaoNovaVenda.getMetodoDePagamento(), vendaAdicionada.getMetodoPagamento());
        assertEquals(requisicaoNovaVenda.getNumParcelas(), vendaAdicionada.getNumParcelas());

        verify(repositorioVendas).adicionarVenda(any(Venda.class));
    }

    @Test
    public void testGetVendas() {
        List<Venda> vendas = new ArrayList<>();
        when(repositorioVendas.getAll()).thenReturn(vendas);

        List<Venda> result = servicoVenda.getVendas();

        assertEquals(vendas, result);
    }

    @Test
    public void testBuscarVendasPorCliente() {
        List<Venda> vendas = new ArrayList<>();
        when(repositorioVendas.getVendasPorCliente(anyString())).thenReturn(vendas);

        List<Venda> result = servicoVenda.buscarVendasPorCliente("123456789");

        assertEquals(vendas, result);
    }

    @Test
    public void testBuscarVendasPorVeiculo() {
        List<Venda> vendas = new ArrayList<>();
        when(repositorioVendas.getVendasPorVeiculo(anyString())).thenReturn(vendas);

        List<Venda> result = servicoVenda.buscarVendasPorVeiculo("Modelo");

        assertEquals(vendas, result);
    }

    @Test
    public void testObterTotalVendasPorPeriodo() {
        BigDecimal totalVendas = BigDecimal.valueOf(5000);
        when(repositorioVendas.obterTotalVendasPorPeriodo(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(totalVendas);

        BigDecimal result = servicoVenda.obterTotalVendasPorPeriodo(LocalDate.now(), LocalDate.now());

        assertEquals(totalVendas, result);
    }

    @Test
    public void testBuscarVendasAcimaValorPorPeriodoEModelo() {
        List<Venda> vendas = new ArrayList<>();
        when(repositorioVendas.getVendasAcimaValorPorPeriodoEModelo(any(BigDecimal.class), any(LocalDate.class),
                any(LocalDate.class), anyString())).thenReturn(vendas);

        List<Venda> result = servicoVenda.buscarVendasAcimaValorPorPeriodoEModelo(BigDecimal.ZERO, LocalDate.now(),
                LocalDate.now(), "Modelo");

        assertEquals(vendas, result);
    }

    @Test
    public void testDeletarVenda() {
        Venda venda = new Venda(1, null, null, null, null, null, 0);
        when(repositorioVendas.getVendaPorId(anyInt())).thenReturn(java.util.Optional.of(venda));

        servicoVenda.deletarVenda(1);
        verify(repositorioVendas).removerVenda(venda);
    }

    @Test
    public void testDeletarVenda_VendaNaoEncontrada() {
        when(repositorioVendas.getVendaPorId(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(VendaNaoEncontradaException.class, () ->
                servicoVenda.deletarVenda(12));
    }
}