package br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioPorEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Musico buscar(String email) {

        Optional<Musico> usuario = usuarioRepository.findByEmail(email);

        if (!usuario.isPresent()) {
            throw new RegistroNaoEncontradoException("Usuario não encontrado!");
        }
        return (usuario.get());
    }
}
