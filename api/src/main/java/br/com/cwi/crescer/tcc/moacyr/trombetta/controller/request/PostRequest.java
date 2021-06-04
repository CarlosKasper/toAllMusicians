package br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Instrumento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Privacidade;
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
