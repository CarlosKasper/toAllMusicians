package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
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