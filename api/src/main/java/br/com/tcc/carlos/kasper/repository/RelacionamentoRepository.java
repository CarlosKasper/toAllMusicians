package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface RelacionamentoRepository extends Repository<Relacionamento, Long> {

    Optional<Relacionamento> findById(Long id);

    Optional<Relacionamento> findByIdAndMusico1Equals(Long id, Musico musico);

    Optional<Relacionamento> findByIdAndMusico2Equals(Long id, Musico musico);

    Optional<Relacionamento> findByMusico1AndMusico2AndStatusEquals(Musico musico1, Musico musico2, Status status);

    List<Relacionamento> findByMusico2AndStatusEquals(Musico musico, Status status);

    Relacionamento save(Relacionamento relacionamento);

    Relacionamento delete(Relacionamento relacionamento);

    boolean existsById(Long id);

    @Query(value = "SELECT USU FROM Musico USU WHERE EXISTS (SELECT 1 FROM Relacionamento REL WHERE REL.musico1.id = :IDMUSICO and REL.musico2.id = USU.id AND REL.status = :STATUS)" +
            "OR EXISTS (SELECT 1 FROM Relacionamento REL WHERE REL.musico2.id = :IDMUSICO AND REL.musico1.id = USU.id AND REL.status = :STATUS)")
    List<Relacionamento> findByRelacionamento(@Param("IDMUSICO") Long idMusico, @Param("STATUS") Status status);

}
