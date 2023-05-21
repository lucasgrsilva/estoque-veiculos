package br.com.concessionaria.domain.dto;

public class DetalhesModeloApiResponse {
    String TipoVeiculo;
    String Valor;
    String Marca;
    String Modelo;
    String AnoModelo;
    String Combustivel;
    String CodigoFipe;
    String MesReferencia;
    String SiglaCombustivel;

    public String getTipoVeiculo() {
        return TipoVeiculo;
    }

    public String getValor() {
        return Valor;
    }

    public String getMarca() {
        return Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public String getAnoModelo() {
        return AnoModelo;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }
}
