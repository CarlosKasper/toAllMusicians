package br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
}