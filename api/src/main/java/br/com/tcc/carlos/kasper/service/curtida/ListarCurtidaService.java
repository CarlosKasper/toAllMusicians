package br.com.tcc.carlos.kasper.service.curtida;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCurtidaService {

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarCurtidaPorPublicacaoService buscarCurtidaPorPublicacaoService;

    public List<Curtida> listar(Long idPublicacao, String usuarioLogado) {

        Post post = buscarPostPorIdService.buscar(idPublicacao);

        List<Curtida> curtidas = buscarCurtidaPorPublicacaoService.buscar(post);

        return curtidas;

    }
}
