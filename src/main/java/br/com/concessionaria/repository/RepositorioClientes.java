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

    public void removerCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public List<Cliente> getAll() {
        return clientes;
    }

    public Optional<Cliente> getClientePorId(int idCliente) {
        return clientes.stream()
                .filter(cli -> cli.getId() == idCliente)
                .findFirst();
    }
    public Optional<Cliente> getClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(cli -> cli.getCpf().equals(cpf))
                .findFirst();
    }

    public int getProximoId() {
        if (clientes.isEmpty()) {
            return 1;
        }

        return clientes.get(clientes.size() - 1).getId() + 1;
    }
}
