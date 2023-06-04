package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.MetodoDePagamento;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoNovaVenda {
    @NotNull(message = "A data da venda deve ser definida")
    private LocalDate dataDaVenda;
    @NotNull(message = "O preço de venda deve ser definido")
    private BigDecimal precoDeVenda;
    @NotNull(message = "O id do Veículo vendido deve ser definido")
    private int idVeiculo;
    @NotNull(message = "O id do Cliente deve ser definido")
    private int idCliente;
    @NotNull(message = "O método de pagamento deve ser definido")
    private MetodoDePagamento metodoDePagamento;
    @NotNull(message = "O número de parcelas deve ser definido")
    private int numParcelas;

    public RequisicaoNovaVenda(LocalDate dataDaVenda, BigDecimal precoDeVenda, int idVeiculo, int idCliente, MetodoDePagamento metodoDePagamento, int numParcelas) {
        this.dataDaVenda = dataDaVenda;
        this.precoDeVenda = precoDeVenda;
        this.idVeiculo = idVeiculo;
        this.idCliente = idCliente;
        this.metodoDePagamento = metodoDePagamento;
        this.numParcelas = numParcelas;
    }

    public LocalDate getDataDaVenda() {
        return dataDaVenda;
    }

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
