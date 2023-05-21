package br.com.concessionaria.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import br.com.concessionaria.domain.dto.DetalhesModeloApiResponse;
import br.com.concessionaria.domain.dto.MarcaApiResponse;
import br.com.concessionaria.domain.dto.ModeloApiResponse;
import br.com.concessionaria.domain.entity.TipoVeiculo;
import br.com.concessionaria.util.Util;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class ServicoFipe {
    public MarcaApiResponse[] buscarFipe(TipoVeiculo tipoVeiculo) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas", tipoVeiculo);

        String response = RequestBuilder(basePath);

        Gson gson = new Gson();
        return gson.fromJson(response, MarcaApiResponse[].class);
    }

    public ModeloApiResponse buscarModelosPorMarca(TipoVeiculo tipoVeiculo, String codMarca) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos", tipoVeiculo, codMarca);

        String response = RequestBuilder(basePath);

        Gson gson = new Gson();
        return gson.fromJson(response, ModeloApiResponse.class);
    }

    public DetalhesModeloApiResponse buscarModelo(TipoVeiculo tipoVeiculo, String codMarca, String codModelo, String ano) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos/%s/anos/%s-3",
                tipoVeiculo, codMarca, codModelo, ano);

        String response = RequestBuilder(basePath);

        Gson gson = new Gson();
        return gson.fromJson(response, DetalhesModeloApiResponse.class);
    }

    private static String RequestBuilder(String uri) throws Exception {
        URL url = new URL(uri);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        return Util.converteJsonEmString(resposta);
    }
}