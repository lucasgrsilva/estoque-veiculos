package br.com.concessionaria.domain.entity;

public class Endereco{
    private String logadouro;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public Endereco(
        final String logadouro,
        final int numero,
        final String bairro,
        final String cep,
        final String estado,
        final String cidade) {
            this.logadouro = logadouro;
            this.numero = numero;
            this.bairro = bairro;
            this.cep = cep;
            this.cidade = cidade;
            this.estado = estado;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}