package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends Repository<Comentario, Long> {

    Optional<Comentario> findById(Long id);

    Optional<Comentario> findByMusicoAndPostEquals(Musico musico, Post post);

    List<Comentario> findByPost(Post post);

    Comentario save(Comentario comentario);

    Comentario delete(Comentario comentario);
}
