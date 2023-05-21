package br.com.concessionaria.service;

import br.com.concessionaria.domain.entity.*;
import br.com.concessionaria.exception.VeiculoInvalidoException;
import br.com.concessionaria.exception.VeiculoNaoEncontradoException;
import br.com.concessionaria.repository.RepositorioVeiculos;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoVeiculo {
    private final RepositorioVeiculos repositorioVeiculos = new RepositorioVeiculos();

    public ServicoVeiculo() {
        Carro carro1 = new Carro(1, 123, "abc1234", "Cruze", 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Chevrolet, 2020,
                LocalDate.parse("2020-06-02"), BigDecimal.valueOf(68000), BigDecimal.valueOf(85000));
        Carro carro2 = new Carro(2, 321, "abc2345", "Civic", 180,
                BigDecimal.valueOf(1000), true, 16, MarcasCarro.Honda, 2019,
                LocalDate.parse("2020-07-02"), BigDecimal.valueOf(65000), BigDecimal.valueOf(75000));
        Moto moto1 = new Moto(3, 234, "abc3456", "PCX", 149, 12,
                MarcasMoto.Honda, 2019, LocalDate.parse("2021-05-08"), BigDecimal.valueOf(9000),
                BigDecimal.valueOf(10000));
        Moto moto2 = new Moto(4, 569, "abc4567", "XRE 300", 291, 15,
                MarcasMoto.Honda, 2020, LocalDate.parse("2021-06-08"), BigDecimal.valueOf(12000),
                BigDecimal.valueOf(14000));

        repositorioVeiculos.adicionarVeiculo(carro1);
        repositorioVeiculos.adicionarVeiculo(carro2);
        repositorioVeiculos.adicionarVeiculo(moto1);
        repositorioVeiculos.adicionarVeiculo(moto2);
    }

    public List<Veiculo> getVeiculos() {
        return repositorioVeiculos.getAll();
    }

    public Veiculo buscarVeiculoPorChassi(int chassi) {
        return repositorioVeiculos.getVeiculoPorChassi(chassi)
                .orElseThrow(VeiculoNaoEncontradoException::new);
    }

    public List<Veiculo> buscarCarroPorMarca(MarcasCarro marcaCarro) {
        return repositorioVeiculos.getCarrosPorMarca(marcaCarro);
    }

    public List<Veiculo> buscarMotoPorMarca(MarcasMoto marcaMoto) {
        return repositorioVeiculos.getMotosPorMarca(marcaMoto);
    }

    public List<Veiculo> buscarVeiculosPorModelo(String modelo) {
        return repositorioVeiculos.getVeiculosPorModelo(modelo);
    }

    public Veiculo buscarVeiculoPorId(int id) {
        return repositorioVeiculos.getVeiculoPorId(id)
                .orElseThrow(VeiculoNaoEncontradoException::new);
    }

    public List<Veiculo> buscarVeiculosPorAnoDeFabricacao(int anoMinimo, int anoMaximo) {
        return repositorioVeiculos.getVeiculosPorAnoFabricação(anoMinimo, anoMaximo);
    }

    public Carro adicionarCarro(Carro novoCarro) {
        //verificando se chassi é único
        Optional<Veiculo> veiculoComMesmoChassi = repositorioVeiculos.getVeiculoPorChassi(novoCarro.getChassi());
        if (veiculoComMesmoChassi.isPresent()) {
            throw new VeiculoInvalidoException("Chassi duplicado");
        }

        //verificando se placa é única
        if (placaDuplicada(novoCarro)) {
            throw new VeiculoInvalidoException("Placa duplicada");
        }

        novoCarro.setId(repositorioVeiculos.getProximoId());
        repositorioVeiculos.adicionarVeiculo(novoCarro);

        return novoCarro;
    }
    public Moto adicionarMoto(Moto novaMoto) {
        //verificando se chassi é único
        Optional<Veiculo> veiculoComMesmoChassi = repositorioVeiculos.getVeiculoPorChassi(novaMoto.getChassi());
        if (veiculoComMesmoChassi.isPresent()) {
            throw new VeiculoInvalidoException("Chassi duplicado");
        }

        //verificando se placa é única
        if (placaDuplicada(novaMoto)) {
            throw new VeiculoInvalidoException("Placa duplicada");
        }

        novaMoto.setId(repositorioVeiculos.getProximoId());
        repositorioVeiculos.adicionarVeiculo(novaMoto);

        return novaMoto;
    }

    public void deletarVeiculo(int id) {
        Veiculo veiculoEncontrado = repositorioVeiculos.getVeiculoPorId(id)
                        .orElseThrow(VeiculoNaoEncontradoException::new);

        repositorioVeiculos.removerVeiculo(veiculoEncontrado);
    }

    public Boolean placaDuplicada(Veiculo veiculo) {
        return repositorioVeiculos.getVeiculoPorPlaca(veiculo.getPlaca()).isPresent();
    }
}
