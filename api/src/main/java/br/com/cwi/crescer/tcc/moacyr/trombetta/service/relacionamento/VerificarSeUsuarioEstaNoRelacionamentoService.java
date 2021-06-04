package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Status;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.RelacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificarSeUsuarioEstaNoRelacionamentoService {

    @Autowired
    private RelacionamentoRepository relacionamentoRepository;

    public Relacionamento verificar(Musico musicoLogado, Musico musico) {

        Optional<Relacionamento> relacionamento1 = relacionamentoRepository.findByMusico1AndMusico2AndStatusEquals(musicoLogado, musico, Status.ACEITO);
        Optional<Relacionamento> relacionamento2 = relacionamentoRepository.findByMusico1AndMusico2AndStatusEquals(musico, musicoLogado, Status.ACEITO);

        if (relacionamento1.isPresent()) {
            return relacionamento1.get();
        }
        if (relacionamento2.isPresent()) {
            return relacionamento2.get();
        }
        throw new RegistroNaoEncontradoException("Relacionamento não encontrado");
    }
}
