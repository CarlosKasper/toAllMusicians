package br.com.tcc.carlos.kasper.service.post;

import br.com.tcc.carlos.kasper.controller.request.PostRequest;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.controller.response.PostResponse;
import br.com.tcc.carlos.kasper.mapper.PublicarPostResponseMapper;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PublicarPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private PublicarPostResponseMapper mapper;

    @Transactional
    public PostResponse publicar(String email, PostRequest postRequest) {

        Musico musico = buscarUsuarioPorEmailService.buscar(email);

        Post post = new Post();
        post.setMusico(musico);
        post.setTitulo(postRequest.getTitulo());
        post.setDataHoraPostagem(LocalDateTime.now());
        post.setPrivacidade(postRequest.getPrivacidade());
        post.setInstrumento(postRequest.getInstrumento());

        Post postSaved = postRepository.save(post);

        return mapper.apply(postSaved);
    }
}