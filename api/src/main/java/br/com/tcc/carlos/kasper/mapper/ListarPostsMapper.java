package br.com.tcc.carlos.kasper.mapper;

import br.com.tcc.carlos.kasper.controller.response.PostFullResponse;
import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListarPostsMapper {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<PostFullResponse> apply(List<Post> postList) {

        List<PostFullResponse> postFullResponseList = new ArrayList<>();

        postList.forEach(post -> {
            List<Curtida> curtidasList = curtidaRepository.findByPost(post);
            List<Comentario> comentarioList = comentarioRepository.findByPost(post);

            PostFullResponse postFullResponse = PostFullResponse.builder()
                    .post(post)
                    .curtidaList(curtidasList)
                    .comentarioList(comentarioList)
                    .build();

            postFullResponseList.add(postFullResponse);
        });

        return postFullResponseList;
    }
}