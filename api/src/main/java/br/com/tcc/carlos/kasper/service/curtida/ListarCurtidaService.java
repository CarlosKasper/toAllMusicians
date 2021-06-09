package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.service.comentario.BuscarComentarioPorPublicacaoService;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarCurtidaService {

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarCurtidaPorPublicacaoService buscarCurtidaPorPublicacaoService;

    public Page<Curtida> listar(Long idPublicacao, String usuarioLogado, Pageable pageable) {

        Post post = buscarPostPorIdService.buscar(idPublicacao);

        Page<Curtida> curtidas = buscarCurtidaPorPublicacaoService.buscar(post, pageable);

        return curtidas;

    }
}
