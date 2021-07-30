package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarUsuarioPorNomeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Musico> buscar(CustomUserDetails currentUser, String nome) {
        List<Musico> users = usuarioRepository.findByNome(nome);
        return users.stream()
                .filter(user -> !currentUser.getId().equals(user.getId()))
                .collect(Collectors.toList());
    }
}