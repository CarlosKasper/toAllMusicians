package br.com.tcc.carlos.kasper.controller.request;

import br.com.tcc.carlos.kasper.domain.Instrumento;
import br.com.tcc.carlos.kasper.domain.Privacidade;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ComentarioRequest {
    @NotEmpty
    private String comentario;
}
