package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.exception.RegistroNaoEncontradoException;
import br.com.tcc.carlos.kasper.repository.RelacionamentoRepository;
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
