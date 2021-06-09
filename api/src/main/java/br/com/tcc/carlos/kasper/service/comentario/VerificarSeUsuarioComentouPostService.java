package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificarSeUsuarioComentouPostService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public boolean verificar(Musico musico, Post post) {

        Optional<Comentario> comentario = comentarioRepository.findByMusicoAndPostEquals(musico, post);
        if (!comentario.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}