package br.com.concessionaria.controller;

import br.com.concessionaria.domain.dto.RequisicaoNovaVenda;
import br.com.concessionaria.domain.dto.VendaResponse;
import br.com.concessionaria.domain.entity.Venda;
import br.com.concessionaria.service.ServicoVenda;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping("api/vendas/periodo")
    public ResponseEntity<BigDecimal> getTotalVendasPorPeriodo(
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        BigDecimal totalVendas = servicoVenda.obterTotalVendasPorPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(totalVendas);
    }

    @GetMapping("api/vendas/relatoriomodelo")
    public ResponseEntity<List<VendaResponse>> buscarVendasAcimaValorPorPeriodoEModelo(
            @RequestParam("valorMinimo") BigDecimal valorMinimo,
            @RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam("modelo") String nomeModelo) {
        List<Venda> vendasEncontradas = servicoVenda.buscarVendasAcimaValorPorPeriodoEModelo(valorMinimo, dataInicio, dataFim, nomeModelo);
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
