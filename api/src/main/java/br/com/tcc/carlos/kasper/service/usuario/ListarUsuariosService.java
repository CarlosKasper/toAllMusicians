package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
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

