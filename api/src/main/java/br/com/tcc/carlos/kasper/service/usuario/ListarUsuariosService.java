package br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public Page<Musico> listar(String email, Pageable pageable) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        return usuarioRepository.findAllUsers(musico.getId(), pageable);
    }
}

