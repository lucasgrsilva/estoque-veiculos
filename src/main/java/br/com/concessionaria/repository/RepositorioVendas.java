package br.com.concessionaria.repository;

import br.com.concessionaria.domain.entity.Venda;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
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

        Predicate<Venda> filtroData = venda -> venda.getDataDaCompra().isAfter(dataInicio.minusDays(1))
                && venda.getDataDaCompra().isBefore(dataFim.plusDays(1));
        
        return vendas.stream()
                .filter(filtroData)
                .map(Venda::getPrecoDeVenda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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
        if (vendas.isEmpty()) {
            return 1;
        }
        return vendas.get(vendas.size() - 1).getId() + 1;
    }

}

