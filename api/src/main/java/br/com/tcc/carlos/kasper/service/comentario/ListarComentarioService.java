package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarComentarioService {

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarComentarioPorPublicacaoService buscarComentarioPorPublicacaoService;

    public List<Comentario> listar(Long idPublicacao, String usuarioLogado) {

        Post post = buscarPostPorIdService.buscar(idPublicacao);

        List<Comentario> comentarios = buscarComentarioPorPublicacaoService.buscar(post);

        return comentarios;

    }
}