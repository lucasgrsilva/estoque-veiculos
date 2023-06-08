package br.com.concessionaria.domain.entity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property= "type")
@JsonSubTypes( {
		@JsonSubTypes.Type(name = "carro", value = Carro.class),
		@JsonSubTypes.Type(name = "moto", value = Moto.class)})
public abstract class Veiculo {
    protected int id;
    protected int chassi;
    protected String placa;
    protected String modelo;
    protected int anoFabricacao;
    protected LocalDate dataDeEntradaEstoque;
    protected BigDecimal valorFipe;
    protected BigDecimal valorComprado;

    public Veiculo(final int id, 
        final int chassi, 
        final String placa, 
        final String modelo,
        final int anoFabricacao, 
        final LocalDate dataDeEntradaEstoque,
        final BigDecimal valorFipe,
        final BigDecimal valorComprado){
            this.id = id ;
            this.chassi = chassi;
            this.placa = placa;
			this.modelo = modelo;
            this.anoFabricacao = anoFabricacao;
            this.dataDeEntradaEstoque = dataDeEntradaEstoque;
            this.valorFipe = valorFipe;
            this.valorComprado = valorComprado;
        }

    public Veiculo() {}

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
