package br.com.cwi.crescer.tcc.moacyr.trombetta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidacaoNegocioException extends RuntimeException {
    public ValidacaoNegocioException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}