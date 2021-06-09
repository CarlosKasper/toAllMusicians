package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ComentarioRepository extends Repository<Comentario, Long> {

    Optional<Comentario> findById(Long id);

    Optional<Comentario> findByMusicoAndPostEquals(Musico musico, Post post);

    Page<Comentario> findByPost(Post post, Pageable pageable);

    Comentario save(Comentario comentario);

    Comentario delete(Comentario comentario);
}
