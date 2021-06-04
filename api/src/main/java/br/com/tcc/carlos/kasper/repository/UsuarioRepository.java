package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Musico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends Repository<Musico, Long> {

    Optional<Musico> findByEmail(String email);

    Optional<Musico> findById(Long id);

    Musico save(Musico musico);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM MUSICO WHERE ID NOT IN (:ID)", nativeQuery = true)
    Page<Musico> findAllUsers(@Param("ID") Long idMusico, Pageable pageable);
}