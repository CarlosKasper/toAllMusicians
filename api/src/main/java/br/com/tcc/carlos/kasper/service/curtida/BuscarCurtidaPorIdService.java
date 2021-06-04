package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
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