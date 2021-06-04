package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificarSeUsuarioCurtiuPostService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    public boolean verificar(Musico musico, Post post) {

        Optional<Curtida> curtida = curtidaRepository.findByMusicoAndPostEquals(musico, post);
        if (!curtida.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}