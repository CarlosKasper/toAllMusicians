package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Service;

@Service
public class VerificarSeRelacionamentoPertenceAoUsuarioService {

    public void verificar(Musico musico, Relacionamento relacionamento) {
        if (!relacionamento.getMusico2().getEmail().equals(musico.getEmail())) {
            throw new ValidacaoNegocioException("Não é possível aceitar uma solicitacão que não pertence");
        }
    }
}