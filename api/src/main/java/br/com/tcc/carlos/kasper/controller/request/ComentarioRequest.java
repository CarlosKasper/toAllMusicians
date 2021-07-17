package br.com.tcc.carlos.kasper.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ComentarioRequest {
    @NotEmpty
    private String comentario;
}