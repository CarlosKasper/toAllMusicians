package br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento;

import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Service;

@Service
public class VerificarSeUsuarioNaoEstaSeConvidandoService {

    public void verificar(String emailSolicitante, String emailSolicitado) {
        if (emailSolicitante.equals(emailSolicitado)) {
            throw new ValidacaoNegocioException("Usuario não pode ter relacionamento com ele mesmo.");
        }
    }
}
