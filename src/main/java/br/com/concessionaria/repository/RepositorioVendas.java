package br.com.concessionaria.repository;

import br.com.concessionaria.domain.entity.Venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositorioVendas {
    private static final List<Venda> vendas = new ArrayList<>();

    public void adicionarVenda(Venda venda) {
        this.vendas.add(venda);
    }

    public void removerVenda(Venda venda) {
        vendas.remove(venda);
    }

    public List<Venda> getAll() {
        return vendas;
    }
    
    public List<Venda> getVendasPorCliente(String cpf) {
	    ArrayList<Venda> vendas = new ArrayList<>();
	    for (Venda venda : this.vendas) {
	        if (venda.getClienteVenda().getCpf().equals(cpf)) {
	            vendas.add(venda);
	        }
	    }
	    return vendas;
	}
    
    public List<Venda> getVendasPorVeiculo(String nomeModelo) {
        ArrayList<Venda> vendas = new ArrayList<>();
        for (Venda venda : this.vendas) {
            if (venda.getVeiculoVendido().getModelo().equals(nomeModelo)) {
                vendas.add(venda);
            }
        }
        return vendas;
    }

    public BigDecimal obterTotalVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        BigDecimal total = BigDecimal.ZERO;
        for (Venda venda : vendas) {
            LocalDate dataVenda = venda.getDataDaCompra();
            if (dataVenda.isAfter(dataInicio) && dataVenda.isBefore(dataFim)) {
                total = total.add(venda.getPrecoDeVenda());
            }
        }
        return total;
    }

    public List<Venda> getVendasAcimaValorPorPeriodoEModelo(BigDecimal valorMinimo, LocalDate dataInicio, LocalDate dataFim, String nomeModelo) {
        return vendas.stream()
                .filter(venda -> venda.getDataDaCompra().isAfter(dataInicio) && venda.getDataDaCompra().isBefore(dataFim))
                .filter(venda -> venda.getVeiculoVendido().getModelo().equalsIgnoreCase(nomeModelo))
                .filter(venda -> venda.getPrecoDeVenda().compareTo(valorMinimo) > 0)
                .collect(Collectors.toList());
    }



    public Optional<Venda> getVendaPorId(int id) {
        return vendas.stream()
                .filter(venda -> venda.getId() == id)
                .findFirst();
    }

    public int getProximoId() {
        return vendas.get(vendas.size() - 1).getId() + 1;
    }

}

