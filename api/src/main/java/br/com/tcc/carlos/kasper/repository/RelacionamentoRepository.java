package br.com.cwi.crescer.tcc.moacyr.trombetta.repository;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface RelacionamentoRepository extends Repository<Relacionamento, Long> {

    Optional<Relacionamento> findById(Long id);

    Optional<Relacionamento> findByIdAndMusico1Equals(Long id, Musico musico);

    Optional<Relacionamento> findByIdAndMusico2Equals(Long id, Musico musico);

    Optional<Relacionamento> findByMusico1AndMusico2AndStatusEquals(Musico musico1, Musico musico2, Status status);

    Page<Relacionamento> findByMusico2AndStatusEquals(Musico musico, Status status, Pageable pageable);

    Relacionamento save(Relacionamento relacionamento);

    Relacionamento delete(Relacionamento relacionamento);

    boolean existsById(Long id);

    @Query(value = "SELECT USU FROM Musico USU WHERE EXISTS (SELECT 1 FROM Relacionamento REL WHERE REL.musico1.id = :IDMUSICO and REL.musico2.id = USU.id AND REL.status = :STATUS)" +
            "OR EXISTS (SELECT 1 FROM Relacionamento REL WHERE REL.musico2.id = :IDMUSICO AND REL.musico1.id = USU.id AND REL.status = :STATUS)")
    Page<Relacionamento> findByRelacionamento(@Param("IDMUSICO") Long idMusico, @Param("STATUS") Status status, Pageable pageable);

}
