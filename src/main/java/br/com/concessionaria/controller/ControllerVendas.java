package br.com.concessionaria.controller;

import br.com.concessionaria.domain.dto.RequisicaoNovaVenda;
import br.com.concessionaria.domain.dto.VendaResponse;
import br.com.concessionaria.domain.entity.Venda;
import br.com.concessionaria.service.ServicoVenda;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControllerVendas {
    private final ServicoVenda servicoVenda;

    public ControllerVendas(ServicoVenda servicoVenda) {
        this.servicoVenda = servicoVenda;
    }

    @GetMapping("api/vendas")
    public ResponseEntity<List<VendaResponse>> listarVendas() {
        List<VendaResponse> responses = servicoVenda.getVendas().stream()
                .map(VendaResponse::new).toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("api/vendas/veiculo/{modelo}")
    public ResponseEntity<List<VendaResponse>> buscarPorVeiculo(@PathVariable String modelo) {
        List<Venda> vendasEncontradas = servicoVenda.buscarVendasPorVeiculo(modelo);

        List<VendaResponse> vendasResponse = vendasEncontradas.stream()
                .map(VendaResponse::new)
                .toList();

        return ResponseEntity.ok(vendasResponse);
    }

    @GetMapping("api/vendas/cliente/{cpf}")
    public ResponseEntity<List<VendaResponse>> buscarPorCliente(@PathVariable String cpf) {
        List<Venda> vendasEncontradas = servicoVenda.buscarVendasPorCliente(cpf);
        List<VendaResponse> vendasResponse = vendasEncontradas.stream()
                .map(VendaResponse::new)
                .toList();

        return ResponseEntity.ok(vendasResponse);
    }

    @PostMapping("api/vendas")
    public ResponseEntity<VendaResponse> adicionarVenda(@RequestBody RequisicaoNovaVenda novaVenda) {
        Venda vendaAdicionada = servicoVenda.adicionarVenda(novaVenda);

        return ResponseEntity.ok(new VendaResponse(vendaAdicionada));
    }

    @DeleteMapping("api/vendas/{id}")
    public ResponseEntity<Boolean> deletarVenda(@PathVariable int id) {
        servicoVenda.deletarVenda(id);

        return ResponseEntity.ok(true);
    }
}
