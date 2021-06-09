package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarComentarioService {

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private BuscarComentarioPorPublicacaoService buscarComentarioPorPublicacaoService;

    public Page<Comentario> listar(Long idPublicacao, String usuarioLogado, Pageable pageable) {

        Post post = buscarPostPorIdService.buscar(idPublicacao);

        Page<Comentario> comentarios = buscarComentarioPorPublicacaoService.buscar(post, pageable);

        return comentarios;

    }
}
