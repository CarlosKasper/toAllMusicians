package br.com.tcc.carlos.kasper.controller.response;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostFullResponse {

    private Post post;
    private List<Curtida> curtidaList;
    private List<Comentario> comentarioList;
}