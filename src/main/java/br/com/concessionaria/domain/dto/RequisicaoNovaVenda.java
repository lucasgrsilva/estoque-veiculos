package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.MetodoDePagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoNovaVenda {
    private BigDecimal precoDeVenda;
    private int idVeiculo;
    private int idCliente;
    private MetodoDePagamento metodoDePagamento;
    private int numParcelas;

    public BigDecimal getPrecoDeVenda() {
        return precoDeVenda;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public MetodoDePagamento getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public int getNumParcelas() {
        return numParcelas;
    }
}
