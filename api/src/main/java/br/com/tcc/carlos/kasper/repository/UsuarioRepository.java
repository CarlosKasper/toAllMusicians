package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Musico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends Repository<Musico, Long> {

    Optional<Musico> findByEmail(String email);

    Optional<Musico> findById(Long id);

    @Query(value = "select * from MUSICO m where m.NOME_COMPLETO like %:NOME%", nativeQuery = true)
    List<Musico> findByNome(@Param("NOME") String nome);

    Musico save(Musico musico);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM MUSICO WHERE ID NOT IN (:ID)", nativeQuery = true)
    List<Musico> findAllUsers(@Param("ID") Long idMusico);
}