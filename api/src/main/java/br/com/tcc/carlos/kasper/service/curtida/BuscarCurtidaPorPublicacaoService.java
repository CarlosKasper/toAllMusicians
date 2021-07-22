package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarCurtidaPorPublicacaoService {

    @Autowired
    private CurtidaRepository curtidaRepository;


    public List<Curtida> buscar(Post post) {

        List<Curtida> curtidas = curtidaRepository.findByPost(post);

        return curtidas;
    }
}