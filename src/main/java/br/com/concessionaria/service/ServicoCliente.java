package br.com.concessionaria.service;

import br.com.concessionaria.domain.dto.RequisicaoNovoCliente;
import br.com.concessionaria.domain.entity.Cliente;
import br.com.concessionaria.domain.entity.Endereco;
import br.com.concessionaria.exception.ClienteInvalidoException;
import br.com.concessionaria.exception.ClienteNaoEncontradoException;
import br.com.concessionaria.repository.RepositorioClientes;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoCliente {
    private final RepositorioClientes repositorioClientes = new RepositorioClientes();

    public ServicoCliente() {
        Endereco endereco1 = new Endereco("Rua dos Partidos", 12, "Goiânia", "12356789", "MG",
                "Belo Horizonte" );
        Endereco endereco2 = new Endereco("Rua dos Partidos", 22, "Goiânia", "12356789", "MG",
                "Belo Horizonte" );

        Cliente cliente1 = new Cliente(1, "Anderson", "123456789", endereco1, "56987548596");
        Cliente cliente2 = new Cliente(2, "Luana", "924456789", endereco2, "18924548596");

        repositorioClientes.adicionarCliente(cliente1);
        repositorioClientes.adicionarCliente(cliente2);
    }

    public List<Cliente> getClientes() {
        return repositorioClientes.getAll();
    }

    public Cliente buscarPorId(int id) {
        return repositorioClientes.getClientePorId(id)
                .orElseThrow(ClienteNaoEncontradoException::new);
    }

    public Cliente adicionarCliente(RequisicaoNovoCliente novoCliente) {
        //verificando se cpf é único
        Optional<Cliente> clienteComMesmoCpf = repositorioClientes.getClientePorCpf(novoCliente.getCpf());
        if(clienteComMesmoCpf.isPresent()) {
            throw new ClienteInvalidoException("CPF duplicado");
        }

        int idNovoCliente = repositorioClientes.getProximoId();

        Cliente cliente = new Cliente(idNovoCliente, novoCliente.getNome(), novoCliente.getTelefone(),
                novoCliente.getEndereco(), novoCliente.getCpf());

        repositorioClientes.adicionarCliente(cliente);

        return cliente;
    }

    public void deletarCliente(int id) {
        Cliente clienteEncontrado = repositorioClientes.getClientePorId(id)
                .orElseThrow(ClienteNaoEncontradoException::new);

        repositorioClientes.removerCliente(clienteEncontrado);
    }
}
