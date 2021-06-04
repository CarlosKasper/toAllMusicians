package br.com.cwi.crescer.tcc.moacyr.trombetta.service.post;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request.PostRequest;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Instrumento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Privacidade;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.mapper.PublicarPostResponseMapper;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

        postRepository.save(post);

        return mapper.apply(post);
    }
}