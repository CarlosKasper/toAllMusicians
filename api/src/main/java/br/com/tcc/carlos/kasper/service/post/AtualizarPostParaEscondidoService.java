package br.com.tcc.carlos.kasper.service.post;

import br.com.tcc.carlos.kasper.controller.request.PostRequest;
import br.com.tcc.carlos.kasper.controller.response.PostResponse;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.domain.Privacidade;
import br.com.tcc.carlos.kasper.mapper.PublicarPostResponseMapper;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AtualizarPostParaEscondidoService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private PublicarPostResponseMapper mapper;

    @Transactional
    public PostResponse esconder(Long postId) {

        Post post = postRepository.findByIdAndPrivacidade(postId);

        post.setPrivacidade(Privacidade.ESCONDIDO);

        postRepository.save(post);

        return mapper.apply(post);
    }
}