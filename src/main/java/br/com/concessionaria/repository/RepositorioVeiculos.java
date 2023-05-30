package br.com.concessionaria.repository;

import br.com.concessionaria.domain.entity.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioVeiculos {
    private static final List<Veiculo> veiculos = new ArrayList<>();

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void removerVeiculo(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public List<Veiculo> getAll() {
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoPorChassi(int chassi) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getChassi() == chassi)
                .findFirst();
    }

    public List<Veiculo> getVeiculosPorAnoFabricação(int anoFabricacaoMinimo, int anoFabricacaoMaximo) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getAnoFabricacao() >= anoFabricacaoMinimo &&
                        veiculo.getAnoFabricacao() <= anoFabricacaoMaximo)
                .toList();
    }

    public List<Veiculo> getCarrosPorMarca(MarcasCarro marcaCarro) {
        return veiculos.stream()
                .filter(veiculo -> {
                    if(!veiculo.getClass().isAssignableFrom(Carro.class)) {
                        return false;
                    }
                    Carro carro = (Carro) veiculo;

                    return carro.getMarca() == marcaCarro;
                })
                .toList();
    }

    public List<Veiculo> getMotosPorMarca(MarcasMoto marcaMoto) {
        return veiculos.stream()
                .filter(veiculo -> {
                    if(!veiculo.getClass().isAssignableFrom(Moto.class)) {
                        return false;
                    }
                    Moto moto = (Moto) veiculo;

                    return moto.getMarca() == marcaMoto;
                })
                .toList();
    }

    public List<Veiculo> getVeiculosPorModelo(String modelo) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getModelo().equals(modelo))
                .toList();
    }

    public Optional<Veiculo> getVeiculoPorPlaca(String placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst();
    }

    public Optional<Veiculo> getVeiculoPorId(int id) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getId() == id)
                .findFirst();
    }

    public int getProximoId() {
        if (veiculos.isEmpty()) {
            return 1;
        }
        return veiculos.get(veiculos.size() - 1).getId() + 1;
    }
}