package br.com.concessionaria.controller;

import br.com.concessionaria.domain.dto.DetalhesModeloApiResponse;
import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.dto.ModeloApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.service.ServicoFipe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerVeiculosFipe {
    private final ServicoFipe servicoFipe;

    public ControllerVeiculosFipe(ServicoFipe servicoFipe) {
        this.servicoFipe = servicoFipe;
    }

    @GetMapping("api/veiculos/fipe/{tipoVeiculo}/marcas")
    public ResponseEntity<MarcaApiResponse[]> listarVeiculosFipe(@PathVariable TipoVeiculo tipoVeiculo)
            throws Exception {
        return ResponseEntity.ok(servicoFipe.buscarFipe(tipoVeiculo));
    }

    @GetMapping("api/veiculos/fipe/{tipoVeiculo}/marcas/{codMarca}")
    public ResponseEntity<ModeloApiResponse> listarVeiculosPorMarca(@PathVariable TipoVeiculo tipoVeiculo,
                                                                    @PathVariable String codMarca) throws Exception {
        return ResponseEntity.ok(servicoFipe.buscarModelosPorMarca(tipoVeiculo, codMarca));
    }

    @GetMapping("api/veiculos/fipe/{tipoVeiculo}/marcas/{codMarca}/modelos/{codModelo}/ano/{ano}")
    public ResponseEntity<DetalhesModeloApiResponse> buscarVeiculoFipe(
            @PathVariable TipoVeiculo tipoVeiculo,
            @PathVariable String codMarca,
            @PathVariable String codModelo,
            @PathVariable String ano)
            throws Exception {
        return ResponseEntity.ok(servicoFipe.buscarModelo(tipoVeiculo, codMarca, codModelo, ano));
    }
}
