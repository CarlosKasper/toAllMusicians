package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuscarUsuarioPorNomeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Musico> buscar(String nome, Pageable pageable) {

        return usuarioRepository.findByNome(nome, pageable);
    }
}
