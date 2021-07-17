package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarComentarioPorPublicacaoService {

    @Autowired
    private ComentarioRepository comentarioRepository;


    public List<Comentario> buscar(Post post) {

        List<Comentario> comentarios = comentarioRepository.findByPost(post);

        return comentarios;
    }
}