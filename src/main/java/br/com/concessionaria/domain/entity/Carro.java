package br.com.concessionaria.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Carro extends Veiculo {
    private int cavalosPotencia;
    private BigDecimal cilindradaEmLitro;
    private Boolean isTurbo;
    private int tipoRodas;
    private MarcasCarro marca;

    public Carro(final int id, 
     final int chassi, 
        final String placa,
        final String modelo,
        final int cavalosPotencia,
        final BigDecimal cilindradaEmLitro,
        final Boolean isTurbo,
        final int tipoRodas,
        final MarcasCarro marca,
        final int anoFabricacao,
        final LocalDate dataDeEntradaEstoque,
        final BigDecimal valorFipe,
        final BigDecimal valorComprado)
	{
    		super(id,chassi,placa,modelo,anoFabricacao,dataDeEntradaEstoque,valorFipe,valorComprado);
            this.cavalosPotencia = cavalosPotencia;
            this.cilindradaEmLitro = cilindradaEmLitro;
            this.isTurbo = isTurbo;
            this.tipoRodas = tipoRodas;
            this.marca = marca;
	}

	public Carro() {
        super();
    }

	public int getCavalosPotencia() {
		return cavalosPotencia;
	}

	public void setCavalosPotencia(int cavalosPotencia) {
		this.cavalosPotencia = cavalosPotencia;
	}

	public BigDecimal getCilindradaEmLitro() {
		return cilindradaEmLitro;
	}

	public void setCilindradaEmLitro(BigDecimal cilindradaEmLitro) {
		this.cilindradaEmLitro = cilindradaEmLitro;
	}

	public Boolean isTurbo() {
		return isTurbo;
	}

	public void setIsTurbo(Boolean isTurbo) {
		this.isTurbo = isTurbo;
	}

	public int getTipoRodas() {
		return tipoRodas;
	}

	public void setTipoRodas(int tipoRodas) {
		this.tipoRodas = tipoRodas;
	}

	public MarcasCarro getMarca() {
		return marca;
	}

	public void setMarca(MarcasCarro marca) {
		this.marca = marca;
	}
}
