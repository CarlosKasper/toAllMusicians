package br.com.tcc.carlos.kasper.interceptor;

import br.com.tcc.carlos.kasper.controller.response.ErrorResponse;
import br.com.tcc.carlos.kasper.exception.RegistroNaoEncontradoException;
import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
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

