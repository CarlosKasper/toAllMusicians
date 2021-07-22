package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarUsuarioPorNomeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Musico> buscar(String nome) {

        return usuarioRepository.findByNome(nome);
    }
}