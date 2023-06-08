package br.com.concessionaria.domain.entity;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Moto extends Veiculo {
    private int cilindradaEmCc;
    private int aroDasRodas;
    private MarcasMoto marca;

    public Moto(final int id, 
        final int chassi, 
        final String placa, 
        final String modelo,
        final int cilindradaEmCc,
        final int aroDasRodas,
        final MarcasMoto marca,
        final int anoFabricacao,
        final LocalDate dataDeEntradaEstoque,
        final BigDecimal valorFipe,
        final BigDecimal valorComprado){
    	super(id, chassi, placa, modelo, anoFabricacao, dataDeEntradaEstoque, valorFipe, valorComprado);
            this.cilindradaEmCc = cilindradaEmCc;
            this.aroDasRodas = aroDasRodas;
            this.marca = marca;
            this.dataDeEntradaEstoque = dataDeEntradaEstoque;
        }

    public Moto() {}

	public int getCilindradaEmCc() {
		return cilindradaEmCc;
	}

	public void setCilindradaEmCc(int cilindradaEmCc) {
		this.cilindradaEmCc = cilindradaEmCc;
	}

	public int getAroDasRodas() {
		return aroDasRodas;
	}

	public void setAroDasRodas(int aroDasRodas) {
		this.aroDasRodas = aroDasRodas;
	}

	public MarcasMoto getMarca() {
		return marca;
	}

	public void setMarca(MarcasMoto marca) {
		this.marca = marca;
	}
    
}
