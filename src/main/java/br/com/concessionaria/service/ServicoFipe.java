package br.com.concessionaria.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


import br.com.concessionaria.domain.dto.DetalhesModeloApiResponse;
import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.dto.ModeloApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.exception.RequisicaoInvalidaException;
import br.com.concessionaria.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ServicoFipe {
    public List<MarcaApiResponse> buscarMarcasFipe(TipoVeiculo tipoVeiculo) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas", tipoVeiculo);

        String response = requestBuilder(basePath);

        Gson gson = new Gson();
        return Arrays.stream(gson.fromJson(response, MarcaApiResponse[].class)).toList();
    }

    public ModeloApiResponse buscarModelosPorMarca(TipoVeiculo tipoVeiculo, String codMarca) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos", tipoVeiculo, codMarca);

        String response = requestBuilder(basePath);

        Gson gson = new Gson();
        return gson.fromJson(response, ModeloApiResponse.class);
    }

    public DetalhesModeloApiResponse buscarModelo(TipoVeiculo tipoVeiculo, String codMarca, String codModelo, String ano)
            throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos/%s/anos/%s-3",
                tipoVeiculo, codMarca, codModelo, ano);

        String response = requestBuilder(basePath);

        Gson gson = new Gson();
        return gson.fromJson(response, DetalhesModeloApiResponse.class);
    }

    private String requestBuilder(String uri) throws Exception {
        URL url = new URL(uri);
        BufferedReader resposta;
        try {
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        }
        catch(Exception exception) {
            throw new RequisicaoInvalidaException();
        }
        return Util.converteJsonEmString(resposta);
    }
}