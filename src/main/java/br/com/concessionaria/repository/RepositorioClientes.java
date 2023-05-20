package br.com.concessionaria.repository;

import br.com.concessionaria.domain.entity.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioClientes {
    private static final List<Cliente> clientes = new ArrayList<>();

    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Optional<Cliente> buscarPorId(int idCliente) {
        return clientes.stream()
                .filter(cli -> cli.getId() == idCliente)
                .findFirst();
    }
}
