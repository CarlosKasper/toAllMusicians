package br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Curtida;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.post.BuscarPostPorIdService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.CurtidaRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarCurtidaService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarCurtidaPorIdService buscarCurtidaPorIdService;

    @Autowired
    private VerificarSeUsuarioCurtiuPostService verificarSeUsuarioCurtiuPostService;

    public void deletar(String email, Long idPost, Long idCurtida) {
        Musico musico = buscarUsuarioPorEmailService.buscar(email);
        Post post = buscarPostPorIdService.buscar(idPost);
        Curtida curtida = buscarCurtidaPorIdService.buscar(idCurtida);

        boolean isPostCurtido = verificarSeUsuarioCurtiuPostService.verificar(musico, post);

        if (isPostCurtido) {
            curtidaRepository.delete(curtida);
        }
    }
}