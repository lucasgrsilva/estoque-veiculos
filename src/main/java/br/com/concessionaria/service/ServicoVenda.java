package br.com.concessionaria.service;

import br.com.concessionaria.domain.dto.RequisicaoNovaVenda;
import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.exception.VendaNaoEncontradaException;
import br.com.concessionaria.repository.RepositorioVendas;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
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
        Veiculo veiculo = servicoVeiculo.buscarVeiculoPorId(novaVenda.getIdVeiculo());

        int idNovaVenda = repositorioVendas.getProximoId();

        Venda venda = new Venda(idNovaVenda, LocalDate.now(), novaVenda.getPrecoDeVenda(), veiculo, cliente,
                novaVenda.getMetodoDePagamento(), novaVenda.getNumParcelas());

        repositorioVendas.adicionarVenda(venda);

        return venda;
    }

    public List<Venda> getVendas() {
        return repositorioVendas.getAll();
    }

    public List<Venda> buscarVendasPorCliente(String cpfCliente) {
        return repositorioVendas.getVendasPorCliente(cpfCliente);
    }

    public List<Venda> buscarVendasPorVeiculo(String modeloVeiculo) {
        return repositorioVendas.getVendasPorVeiculo(modeloVeiculo);
    }

    public void deletarVenda(int id) {
        Venda vendaEncontrada = repositorioVendas.getVendaPorId(id)
                .orElseThrow(VendaNaoEncontradaException::new);

        repositorioVendas.removerVenda(vendaEncontrada);
    }
}
