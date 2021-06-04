package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.exception.RegistroNaoEncontradoException;
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
