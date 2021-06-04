package br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Curtida;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.CurtidaRepository;
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