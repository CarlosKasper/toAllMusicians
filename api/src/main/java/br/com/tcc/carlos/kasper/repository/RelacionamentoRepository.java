package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.domain.Status;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RelacionamentoRepository extends Repository<Relacionamento, Long> {

    Optional<Relacionamento> findById(Long id);

    Optional<Relacionamento> findByMusico1AndMusico2AndStatusEquals(Musico musico1, Musico musico2, Status status);

    List<Relacionamento> findByMusico2AndStatusEquals(Musico musico, Status status);

    Relacionamento save(Relacionamento relacionamento);

    Relacionamento delete(Relacionamento relacionamento);

    List<Relacionamento> findByMusico1OrMusico2AndStatusEquals(Musico musico1, Musico musico2, Status status);
}