package br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Curtida;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarCurtidaPorIdService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    public Curtida buscar(Long id) {

        Optional<Curtida> curtida = curtidaRepository.findById(id);

        if (!curtida.isPresent()) {
            throw new ValidacaoNegocioException("Curtida não encontrada.");
        }
        return (curtida.get());
    }
}