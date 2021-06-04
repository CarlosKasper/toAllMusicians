package br.com.cwi.crescer.tcc.moacyr.trombetta.repository;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Curtida;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CurtidaRepository extends Repository<Curtida, Long> {

    Optional<Curtida> findById(Long id);

    Optional<Curtida> findByMusicoAndPostEquals(Musico musico, Post post);

    Curtida save(Curtida curtida);

    Curtida delete(Curtida curtida);
}
