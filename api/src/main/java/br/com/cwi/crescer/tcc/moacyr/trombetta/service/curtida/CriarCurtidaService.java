package br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Curtida;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.ValidacaoNegocioException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.PostRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.post.BuscarPostPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarCurtidaService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private VerificarSeUsuarioCurtiuPostService verificarSeUsuarioCurtiuPostService;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    public void criar(String email, Long idPost) {
        Musico musico = buscarUsuarioPorEmailService.buscar(email);
        Post post = buscarPostPorIdService.buscar(idPost);

        boolean isPostCurtido = verificarSeUsuarioCurtiuPostService.verificar(musico, post);

        if (!isPostCurtido) {
            Curtida curtida = new Curtida();
            curtida.setPost(post);
            curtida.setMusico(musico);
            curtidaRepository.save(curtida);
        } else {
            throw new ValidacaoNegocioException("Post já curtido");
        }
    }
}
