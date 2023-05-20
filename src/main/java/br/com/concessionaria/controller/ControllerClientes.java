package br.com.concessionaria.controller;

import br.com.concessionaria.domain.dto.RequisicaoNovoCliente;
import br.com.concessionaria.domain.entity.Cliente;
import br.com.concessionaria.service.ServicoCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerClientes {
    private final ServicoCliente servicoCliente;

    public ControllerClientes(ServicoCliente servicoCliente) {
        this.servicoCliente = servicoCliente;
    }

    @GetMapping("api/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(servicoCliente.getClientes());
    }

    @GetMapping("api/clientes/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable int id) {
        Cliente clienteEncontrado = servicoCliente.buscarPorId(id);

        return ResponseEntity.ok(clienteEncontrado);
    }

    @PostMapping("api/clientes")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody RequisicaoNovoCliente novoCliente) {
        Cliente clienteSalvo = servicoCliente.adicionarCliente(novoCliente);

        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("api/clientes/{id}")
    public ResponseEntity<Boolean> deletarCliente(@PathVariable int id) {
        servicoCliente.deletarCliente(id);

        return ResponseEntity.ok(true);
    }
}
