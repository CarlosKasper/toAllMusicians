package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Status;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificarSeJaExistePedidoDeRelacionamentoService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    public void verificar(Musico musico1, Musico musico2) {

        Optional<Relacionamento> relacionamento1 = relacionamentoRepository.findByMusico1AndMusico2AndStatusEquals(musico1, musico2, Status.PENDENTE);

        Optional<Relacionamento> relacionamento2 = relacionamentoRepository.findByMusico1AndMusico2AndStatusEquals(musico2, musico1, Status.PENDENTE);

        if (relacionamento1.isPresent() || relacionamento2.isPresent()) {
            throw new ValidacaoNegocioException("A amizade já possui um convite pendente.");
        }
    }
}