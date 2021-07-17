package br.com.tcc.carlos.kasper.controller.request;

import br.com.tcc.carlos.kasper.domain.Instrumento;
import br.com.tcc.carlos.kasper.domain.Privacidade;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostRequest {

    @NotEmpty
    private Long id;

    @NotEmpty
    private String titulo;

    @NotEmpty
    Privacidade privacidade;

    private Instrumento instrumento;
}