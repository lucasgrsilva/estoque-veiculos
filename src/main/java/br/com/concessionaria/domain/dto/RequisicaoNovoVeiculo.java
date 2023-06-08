package br.com.concessionaria.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class RequisicaoNovoVeiculo {
    protected int chassi;
    protected String placa;
    protected String modelo;
    protected int anoFabricacao;
    protected LocalDate dataDeEntradaEstoque;
    protected BigDecimal valorFipe;
    protected BigDecimal valorComprado;

    public RequisicaoNovoVeiculo(
            int chassi,
            String placa,
            String modelo,
            int anoFabricacao,
            LocalDate dataDeEntradaEstoque,
            BigDecimal valorFipe,
            BigDecimal valorComprado) {
        this.chassi = chassi;
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.dataDeEntradaEstoque = dataDeEntradaEstoque;
        this.valorFipe = valorFipe;
        this.valorComprado = valorComprado;
    }

    public RequisicaoNovoVeiculo() {}

    public int getChassi() {
        return chassi;
    }

    public void setChassi(int chassi) {
        this.chassi = chassi;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public LocalDate getDataDeEntradaEstoque() {
        return dataDeEntradaEstoque;
    }

    public void setDataDeEntradaEstoque(LocalDate dataDeEntradaEstoque) {
        this.dataDeEntradaEstoque = dataDeEntradaEstoque;
    }

    public BigDecimal getValorFipe() {
        return valorFipe;
    }

    public void setValorFipe(BigDecimal valorFipe) {
        this.valorFipe = valorFipe;
    }

    public BigDecimal getValorComprado() {
        return valorComprado;
    }

    public void setValorComprado(BigDecimal valorComprado) {
        this.valorComprado = valorComprado;
    }
}
