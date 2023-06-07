package br.com.concessionaria.unidade;

import br.com.concessionaria.domain.dto.RequisicaoNovoCliente;
import br.com.concessionaria.domain.entity.Cliente;
import br.com.concessionaria.domain.entity.Endereco;
import br.com.concessionaria.exception.ClienteInvalidoException;
import br.com.concessionaria.exception.ClienteNaoEncontradoException;
import br.com.concessionaria.repository.RepositorioClientes;
import br.com.concessionaria.service.ServicoCliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ServicoClienteTest {
    @Mock
    RepositorioClientes repositorioClientes;

    @InjectMocks
    ServicoCliente servicoCliente;

    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        endereco = new Endereco("Rua A", 10, "Federal", "12.345-678", "MG", "Contagem");
    }

    @Test
    public void testAdicionarCliente_ClienteNovo() {

        RequisicaoNovoCliente novoCliente = new RequisicaoNovoCliente("Elon", "3140028922", endereco, "12345678910");

        int proximoId = 13;

        when(repositorioClientes.getClientePorCpf(novoCliente.getCpf())).thenReturn(Optional.empty());
        when(repositorioClientes.getProximoId()).thenReturn(13);

        Cliente clienteAdicionado = servicoCliente.adicionarCliente(novoCliente);

        verify(repositorioClientes).adicionarCliente(clienteAdicionado);

        assertEquals(proximoId, clienteAdicionado.getId());
        assertEquals(novoCliente.getNome(), clienteAdicionado.getNome());
        assertEquals(novoCliente.getCpf(), clienteAdicionado.getCpf());
        assertEquals(novoCliente.getTelefone(), clienteAdicionado.getTelefone());
    }

    @Test
    public void testAdicionarCliente_ClienteComCPFRepetidoLancaExcecao() {
        RequisicaoNovoCliente novoCliente = new RequisicaoNovoCliente("Elon", "3140028922", endereco, "12345678910");

        when(repositorioClientes.getClientePorCpf(novoCliente.getCpf())).thenReturn(Optional.of(new Cliente()));
        var excecaoLancada = assertThrows(ClienteInvalidoException.class, () ->
                servicoCliente.adicionarCliente(novoCliente));

        assertEquals("CPF duplicado", excecaoLancada.getMessage());
    }

    @Test
    public void testBuscarPorId_ClientePresente() {
        Cliente clientePresente = new Cliente(12, "Elon", "3140028922", endereco, "12345678910");

        when(repositorioClientes.getClientePorId(12)).thenReturn(Optional.of(clientePresente));

        Cliente cliente = servicoCliente.buscarPorId(12);

        assertEquals(clientePresente, cliente);
    }

    @Test
    public void testBuscarPorId_ClienteNaoEncontrado() {
        when(repositorioClientes.getClientePorId(11)).thenReturn(Optional.empty());

        assertThrows(ClienteNaoEncontradoException.class, () ->
                servicoCliente.buscarPorId(11));
    }

    @Test
    public void testDeletarCliente_ClienteExistente() {
        Cliente clienteExistente = new Cliente(1, "Anderson", "123456789", endereco, "56987548596");

        when(repositorioClientes.getClientePorId(1)).thenReturn(Optional.of(clienteExistente));
        doNothing().when(repositorioClientes).removerCliente(clienteExistente);

        servicoCliente.deletarCliente(1);
        verify(repositorioClientes).removerCliente(clienteExistente);
    }

    @Test
    public void testDeletarCliente_ClienteNaoEncontrado() {
        when(repositorioClientes.getClientePorId(11)).thenReturn(Optional.empty());

        Assertions.assertThrows(ClienteNaoEncontradoException.class, () ->
                servicoCliente.deletarCliente(11));

        verify(repositorioClientes, never()).removerCliente(any(Cliente.class));
    }

    @Test
    public void testGetClientes() {
        Cliente cliente1 = new Cliente(1, "Anderson", "123456789", endereco, "56987548596");
        Cliente cliente2 = new Cliente(2, "Luana", "924456789", endereco, "18924548596");
        List<Cliente> clientes = List.of(cliente1, cliente2);

        when(repositorioClientes.getAll()).thenReturn(clientes);

        List<Cliente> result = servicoCliente.getClientes();

        Assertions.assertEquals(clientes, result);
    }
}
