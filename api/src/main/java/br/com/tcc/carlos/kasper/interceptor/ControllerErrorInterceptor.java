package br.com.cwi.crescer.tcc.moacyr.trombetta.interceptor;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.ErrorResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerErrorInterceptor {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ValidacaoNegocioException exception) {

        ErrorResponse error = ErrorResponse.builder()
                .httpStatus(exception.getStatus())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(error, exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(RegistroNaoEncontradoException exception) {

        ErrorResponse error = ErrorResponse.builder()
                .httpStatus(exception.getStatus())
                .message(exception.getMessage()).build();

        return new ResponseEntity<>(error, exception.getStatus());
    }
}

