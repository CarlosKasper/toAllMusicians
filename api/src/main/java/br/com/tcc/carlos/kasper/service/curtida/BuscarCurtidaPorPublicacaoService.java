package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarCurtidaPorPublicacaoService {

    @Autowired
    private CurtidaRepository curtidaRepository;


    public Page<Curtida> buscar(Post post, Pageable pageable) {

        Page<Curtida> curtidas = curtidaRepository.findByPost(post, pageable);

        return curtidas;
    }
}
