package br.com.tcc.carlos.kasper.repository;

import br.com.tcc.carlos.kasper.domain.Imagem;
import org.springframework.data.repository.Repository;

public interface ImagemRepository extends Repository<Imagem, Long> {

    Imagem save(Imagem imagem);
}