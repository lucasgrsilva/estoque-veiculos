package br.com.concessionaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VeiculoInvalidoException extends RuntimeException {
    public VeiculoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
