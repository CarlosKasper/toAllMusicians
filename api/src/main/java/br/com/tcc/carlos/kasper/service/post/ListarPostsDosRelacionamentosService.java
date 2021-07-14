package br.com.tcc.carlos.kasper.service.post;

import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPostsDosRelacionamentosService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public List<Post> listar(String email) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        return postRepository.findByFriendsPost(musico.getId());
    }
}