package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.MarcasMoto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RequisicaoNovaMoto extends RequisicaoNovoVeiculo {
    private int cilindradaEmCc;
    private int aroDasRodas;
    private MarcasMoto marca;

    public RequisicaoNovaMoto(
            int chassi,
            String placa,
            String modelo,
            int anoFabricacao,
            LocalDate dataDeEntradaEstoque,
            BigDecimal valorFipe,
            BigDecimal valorComprado,
            int cilindradaEmCc,
            int aroDasRodas,
            MarcasMoto marca) {
        super(chassi, placa, modelo, anoFabricacao, dataDeEntradaEstoque, valorFipe, valorComprado);
        this.cilindradaEmCc = cilindradaEmCc;
        this.aroDasRodas = aroDasRodas;
        this.marca = marca;
    }

    public RequisicaoNovaMoto() {}

    public int getCilindradaEmCc() {
        return cilindradaEmCc;
    }

    public int getAroDasRodas() {
        return aroDasRodas;
    }

    public MarcasMoto getMarca() {
        return marca;
    }


}
