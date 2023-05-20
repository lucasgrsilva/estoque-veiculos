package br.com.concessionaria.service;

import br.com.concessionaria.domain.entity.Cliente;
import br.com.concessionaria.domain.entity.Endereco;
import br.com.concessionaria.exception.ClienteNaoEncontradoException;
import br.com.concessionaria.repository.RepositorioClientes;
import org.springframework.stereotype.Service;

@Service
public class ServicoCliente {
    private final RepositorioClientes repositorioClientes = new RepositorioClientes();

    public ServicoCliente() {
        Endereco endereco1 = new Endereco("Rua dos Partidos", 12, "Goiânia", "12356789", "MG",
                "Belo Horizonte" );
        Endereco endereco2 = new Endereco("Rua dos Partidos", 22, "Goiânia", "12356789", "MG",
                "Belo Horizonte" );

        Cliente cliente1 = new Cliente("Anderson", "123456789", endereco1, "56987548596", 1);
        Cliente cliente2 = new Cliente("Luana", "924456789", endereco1, "18924548596", 2);

        repositorioClientes.adicionarCliente(cliente1);
        repositorioClientes.adicionarCliente(cliente2);
    }

    public Cliente buscarPorId(int id) {
        return repositorioClientes.buscarPorId(id)
                .orElseThrow(ClienteNaoEncontradoException::new);
    }
}
