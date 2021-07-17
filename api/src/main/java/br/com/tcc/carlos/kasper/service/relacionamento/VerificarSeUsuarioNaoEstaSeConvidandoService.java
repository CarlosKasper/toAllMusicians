package br.com.tcc.carlos.kasper.service.relacionamento;

import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
import org.springframework.stereotype.Service;

@Service
public class VerificarSeUsuarioNaoEstaSeConvidandoService {

    public void verificar(String emailSolicitante, String emailSolicitado) {
        if (emailSolicitante.equals(emailSolicitado)) {
            throw new ValidacaoNegocioException("Usuario não pode ter relacionamento com ele mesmo.");
        }
    }
}