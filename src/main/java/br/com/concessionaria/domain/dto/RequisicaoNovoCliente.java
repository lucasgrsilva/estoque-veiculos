package br.com.concessionaria.domain.dto;

import br.com.concessionaria.domain.entity.Endereco;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequisicaoNovoCliente {
    @NotEmpty(message = "O nome do cliente deve ser definido")
    private String nome;
    @NotEmpty(message = "O telefone do cliente deve ser definido")
    private String telefone;
    @NotEmpty(message = "O endereço do cliente deve ser definido")
    private Endereco endereco;
    @NotEmpty(message = "O CPF do cliente deve ser definido")
    private String cpf;

    public RequisicaoNovoCliente(String nome, String telefone, Endereco endereco, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }
}
