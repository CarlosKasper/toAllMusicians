package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import br.com.tcc.carlos.kasper.repository.PostRepository;
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