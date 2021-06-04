package br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarUsuarioPorIdService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Musico buscar(Long id) {

        Optional<Musico> musico = usuarioRepository.findById(id);

        if (!musico.isPresent()) {
            throw new RegistroNaoEncontradoException("Usuario não encontrado!");
        }

        return (musico.get());
    }
}
