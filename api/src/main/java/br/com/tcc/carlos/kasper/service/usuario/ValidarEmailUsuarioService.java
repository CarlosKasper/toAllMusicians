package br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario;

import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidarEmailUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarSeExistente(String email) {

        boolean isUsuarioExistente = usuarioRepository.existsByEmail(email);

        if (isUsuarioExistente) {
            throw new ValidacaoNegocioException("Erro ao cadastrar! O Email já está em uso.");
        }
    }
}
