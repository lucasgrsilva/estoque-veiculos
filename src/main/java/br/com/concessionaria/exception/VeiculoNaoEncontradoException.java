package br.com.concessionaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VeiculoNaoEncontradoException extends  RuntimeException {
    public VeiculoNaoEncontradoException() {
        super("Veículo não encontrado");
    }
}
