package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends Repository<Post, Long> {

    Optional<Post> findById(Long id);

    @Query(value = "SELECT * FROM POST P WHERE P.ID = :ID_POST", nativeQuery = true)
    Post findByIdAndPrivacidade(@Param("ID_POST") Long id);

    @Query(value = "SELECT * FROM POST P WHERE P.IDMUSICO = :ID_MUSICO AND P.PRIVACIDADE = 'PUBLICO'", nativeQuery = true)
    List<Post> findByMusicoandPrivacidade(@Param("ID_MUSICO") Long id);

    @Query(value = "SELECT * FROM POST P WHERE P.IDMUSICO = :ID_MUSICO AND (P.PRIVACIDADE = 'PUBLICO' OR P.PRIVACIDADE = 'PRIVADO')", nativeQuery = true)
    List<Post> findByMusico(@Param("ID_MUSICO") Long id);

    Optional<Post> findByTitulo(Long id);

    Post save(Post post);

    @Query(value = "SELECT DISTINCT P.* FROM POST P " +
                "LEFT JOIN RELACIONAMENTO REL1 ON P.IDMUSICO = REL1.IDMUSICO1 " +
                "LEFT JOIN RELACIONAMENTO REL2 ON P.IDMUSICO = REL2.IDMUSICO2 " +
                "WHERE (P.PRIVACIDADE = 'PUBLICO' AND P.IDMUSICO = :ID_MUSICO) OR P.PRIVACIDADE = 'PUBLICO'" +
                "AND ((P.IDMUSICO IN (SELECT IDMUSICO1 FROM RELACIONAMENTO WHERE IDMUSICO2 = :ID_MUSICO) AND REL1.STATUS = 'ACEITO') " +
                "OR (P.IDMUSICO IN (SELECT IDMUSICO2 FROM RELACIONAMENTO WHERE IDMUSICO1 = :ID_MUSICO) AND REL2.STATUS = 'ACEITO')) " +
                "ORDER BY DATA_HORA DESC", nativeQuery = true)
    List<Post> findByFriendsPost(@Param("ID_MUSICO") Long id);

    @Query(value = "SELECT * FROM POST", nativeQuery = true)
    List<Post> findAll();

    boolean existsById(Long id);
}
