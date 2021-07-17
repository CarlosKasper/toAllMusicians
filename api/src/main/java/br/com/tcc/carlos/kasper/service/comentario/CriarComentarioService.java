package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public void criar(String email, Long idPost, String comentarioMusico) {
        Musico musico = buscarUsuarioPorEmailService.buscar(email);
        Post post = buscarPostPorIdService.buscar(idPost);

        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioMusico);
        comentario.setPost(post);
        comentario.setMusico(musico);

        comentarioRepository.save(comentario);
    }
}