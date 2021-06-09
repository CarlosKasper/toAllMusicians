package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarComentarioPorPublicacaoService {

    @Autowired
    private ComentarioRepository comentarioRepository;


    public Page<Comentario> buscar(Post post, Pageable pageable) {

        Page<Comentario> comentarios = comentarioRepository.findByPost(post, pageable);

        return comentarios;
    }
}
