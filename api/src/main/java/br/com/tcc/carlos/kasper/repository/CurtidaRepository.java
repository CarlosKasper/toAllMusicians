package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CurtidaRepository extends Repository<Curtida, Long> {

    Optional<Curtida> findById(Long id);

    Optional<Curtida> findByMusicoAndPostEquals(Musico musico, Post post);

    List<Curtida> findByPost(Post post);

    Curtida save(Curtida curtida);

    Curtida delete(Curtida curtida);
}
