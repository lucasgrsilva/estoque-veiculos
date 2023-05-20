package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.Cliente;
import br.com.concessionaria.domain.entity.MetodoDePagamento;
import br.com.concessionaria.domain.entity.Veiculo;
import br.com.concessionaria.domain.entity.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VendaResponse {
    private final int id;
    private final LocalDate dataDaCompra;
    private final BigDecimal precoDeVenda;
    private final int idVeiculo;
    private final String modeloVeiculo;
    private final int idCliente;
    private final String nomeCliente;
    private final MetodoDePagamento metodoPagamento;
    private final int numParcelas;

    public VendaResponse(Venda venda) {
        id = venda.getId();
        dataDaCompra = venda.getDataDaCompra();
        precoDeVenda = venda.getPrecoDeVenda();
        idVeiculo = venda.getVeiculoVendido().getId();
        modeloVeiculo = venda.getVeiculoVendido().getModelo();
        idCliente = venda.getClienteVenda().getId();
        nomeCliente = venda.getClienteVenda().getNome();
        metodoPagamento = venda.getMetodoPagamento();
        numParcelas = venda.getNumParcelas();
    }

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public BigDecimal getPrecoDeVenda() {
        return precoDeVenda;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public MetodoDePagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public int getNumParcelas() {
        return numParcelas;
    }
}
