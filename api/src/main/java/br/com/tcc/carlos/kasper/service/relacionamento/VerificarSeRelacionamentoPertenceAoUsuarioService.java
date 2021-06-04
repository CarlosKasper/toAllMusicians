package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Service;

@Service
public class VerificarSeRelacionamentoPertenceAoUsuarioService {

    public void verificar(Musico musico, Relacionamento relacionamento) {
        if (!relacionamento.getMusico2().getEmail().equals(musico.getEmail())) {
            throw new ValidacaoNegocioException("Não é possível aceitar uma solicitacão que não pertence");
        }
    }
}