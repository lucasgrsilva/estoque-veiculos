package br.com.concessionaria.service;

import br.com.concessionaria.domain.dto.RequisicaoNovaVenda;
import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.repository.RepositorioVendas;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServicoVenda {
    private final RepositorioVendas repositorioVendas = new RepositorioVendas();
    private final ServicoVeiculo servicoVeiculo;
    private final ServicoCliente servicoCliente;
    public ServicoVenda(ServicoVeiculo servicoVeiculo, ServicoCliente servicoCliente) {
        this.servicoVeiculo = servicoVeiculo;
        this.servicoCliente = servicoCliente;
    }

    public Venda adicionarVenda(RequisicaoNovaVenda novaVenda) {
        //verificando se cliente está cadastrado
        Cliente cliente = servicoCliente.buscarPorId(novaVenda.getIdCliente());

        //verificando se veiculo está cadastrado
        Veiculo veiculo = servicoVeiculo.buscarPorId(novaVenda.getIdVeiculo());

        Venda venda = new Venda(LocalDate.now(), novaVenda.getPrecoDeVenda(), veiculo, cliente,
                novaVenda.getMetodoDePagamento(), novaVenda.getNumParcelas());

        repositorioVendas.addVenda(venda);

        return venda;
    }
}
