package br.com.concessionaria.controller;

import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.service.ServicoVeiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerVeiculos {
    private final ServicoVeiculo servicoVeiculo;

    public ControllerVeiculos(ServicoVeiculo servicoVeiculo) {
        this.servicoVeiculo = servicoVeiculo;
    }

    @GetMapping("api/veiculos/chassi/{chassi}")
    public ResponseEntity<Veiculo> buscarPorChasi(@PathVariable int chassi) {
        Veiculo veiculoEncontrado = servicoVeiculo.buscarPorChassi(chassi);

        return ResponseEntity.ok(veiculoEncontrado);
    }
    @GetMapping("api/veiculos/carros/{marca}")
    public ResponseEntity<List<Veiculo>> buscarCarroPorMarca(@PathVariable MarcasCarro marca) {
        List<Veiculo> veiculosEncontrados = servicoVeiculo.buscarCarroPorMarca(marca);

        return ResponseEntity.ok(veiculosEncontrados);
    }
    @GetMapping("api/veiculos/motos/{marca}")
    public ResponseEntity<List<Veiculo>> buscarMotoPorMarca(@PathVariable MarcasMoto marca) {
        List<Veiculo> veiculosEncontrados = servicoVeiculo.buscarMotoPorMarca(marca);

        return ResponseEntity.ok(veiculosEncontrados);
    }

    @GetMapping("api/veiculos/{modelo}")
    public ResponseEntity<List<Veiculo>> buscarPorModelo(@PathVariable String modelo) {
        List<Veiculo> veiculosEncontrados = servicoVeiculo.buscarPorModelo(modelo);

        return ResponseEntity.ok(veiculosEncontrados);
    }

    @PostMapping("api/veiculos/carros")
    public ResponseEntity<Carro> adicionarCarro(@RequestBody Carro novoCarro) {
        Carro carroSalvo = servicoVeiculo.adicionarCarro(novoCarro);

        return ResponseEntity.ok(carroSalvo);
    }

    @PostMapping("api/veiculos/motos")
    public ResponseEntity<Moto> adicionarMoto(@RequestBody Moto novaMoto) {
        Moto motoSalva = servicoVeiculo.adicionarMoto(novaMoto);

        return ResponseEntity.ok(motoSalva);
    }
}
