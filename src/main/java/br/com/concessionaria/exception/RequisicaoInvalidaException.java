package br.com.concessionaria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequisicaoInvalidaException extends  RuntimeException {
    public RequisicaoInvalidaException() {
        super("Dados de marca ou veículo inválidos");
    }
}
