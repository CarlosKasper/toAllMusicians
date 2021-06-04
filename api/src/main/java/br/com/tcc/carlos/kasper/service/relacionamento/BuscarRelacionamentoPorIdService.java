package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarRelacionamentoPorIdService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    public Relacionamento buscar(Long id) {

        Optional<Relacionamento> relacionamento = relacionamentoRepository.findById(id);

        if (!relacionamento.isPresent()) {
            throw new RegistroNaoEncontradoException("Relacionamento não encontrado!");
        }
        return (relacionamento.get());
    }
}
