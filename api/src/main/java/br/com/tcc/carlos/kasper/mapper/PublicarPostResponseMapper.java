package br.com.tcc.carlos.kasper.mapper;

import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.controller.response.PostResponse;
import org.springframework.stereotype.Component;

@Component
public class PublicarPostResponseMapper {

    public PostResponse apply(Post post) {

        PostResponse response = new PostResponse();

        response.setTitulo(post.getTitulo());
        response.setDataHoraPostagem(post.getDataHoraPostagem());

        return response;
    }
}
