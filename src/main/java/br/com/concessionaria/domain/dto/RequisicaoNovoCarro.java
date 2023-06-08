package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.MarcasCarro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoNovoCarro extends RequisicaoNovoVeiculo {
    private int cavalosPotencia;
    private BigDecimal cilindradaEmLitro;
    private Boolean turbo;
    private int tipoRodas;
    private MarcasCarro marca;

    public RequisicaoNovoCarro(
            int chassi,
            String placa,
            String modelo,
            int anoFabricacao,
            LocalDate dataDeEntradaEstoque,
            BigDecimal valorFipe,
            BigDecimal valorComprado,
            int cavalosPotencia,
            BigDecimal cilindradaEmLitro,
            Boolean turbo,
            int tipoRodas,
            MarcasCarro marca) {
        super(chassi, placa, modelo, anoFabricacao, dataDeEntradaEstoque, valorFipe, valorComprado);
        this.cavalosPotencia = cavalosPotencia;
        this.cilindradaEmLitro = cilindradaEmLitro;
        this.turbo = turbo;
        this.tipoRodas = tipoRodas;
        this.marca = marca;
    }

    public RequisicaoNovoCarro() {}

    public int getCavalosPotencia() {
        return cavalosPotencia;
    }

    public BigDecimal getCilindradaEmLitro() {
        return cilindradaEmLitro;
    }

    public Boolean isTurbo() {
        return turbo;
    }

    public int getTipoRodas() {
        return tipoRodas;
    }

    public MarcasCarro getMarca() {
        return marca;
    }
}
