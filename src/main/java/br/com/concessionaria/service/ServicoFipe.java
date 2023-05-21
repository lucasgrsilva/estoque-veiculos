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

        URL url = new URL(basePath);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = Util.converteJsonEmString(resposta);

        Gson gson = new Gson();
        return gson.fromJson(jsonEmString, MarcaApiResponse[].class);
    }

    public ModeloApiResponse buscarModelosPorMarca(TipoVeiculo tipoVeiculo, String codMarca) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos", tipoVeiculo, codMarca);

        URL url = new URL(basePath);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = Util.converteJsonEmString(resposta);

        Gson gson = new Gson();
        return gson.fromJson(jsonEmString, ModeloApiResponse.class);
    }

    public DetalhesModeloApiResponse buscarModelo(TipoVeiculo tipoVeiculo, String codMarca, String codModelo, String ano) throws Exception{

        String basePath = String.format("https://parallelum.com.br/fipe/api/v1/%s/marcas/%s/modelos/%s/anos/%s-3",
                tipoVeiculo, codMarca, codModelo, ano);

        URL url = new URL(basePath);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = Util.converteJsonEmString(resposta);

        Gson gson = new Gson();
        return gson.fromJson(jsonEmString, DetalhesModeloApiResponse.class);
    }


}