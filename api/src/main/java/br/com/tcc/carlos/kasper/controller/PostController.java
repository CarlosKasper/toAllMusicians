package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.request.PostRequest;
import br.com.tcc.carlos.kasper.controller.response.PostFullResponse;
import br.com.tcc.carlos.kasper.mapper.ListarPostsMapper;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.post.*;
import br.com.tcc.carlos.kasper.controller.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RolesAllowed({"ROLE_USUARIO"})
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PublicarPostService publicarAtividadeService;

    @Autowired
    private ListarPostsDosRelacionamentosService listarPostsDosRelacionamentosService;

    @Autowired
    private ListarPostDoUsuarioService listarPostDoUsuarioService;

    @Autowired
    private ListarPostDoUsuarioEspecificoService listarPostDoUsuarioEspecificoService;

    @Autowired
    private ListarTodosPostsService listarTodosPosts;

    @Autowired
    private AtualizarPostParaEscondidoService atualizarPostParaEscondidoService;

    @Autowired
    private ListarPostsMapper listarPostsMapper;

    @PostMapping("/publicar")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse publicar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @RequestBody PostRequest postRequest) {

        return publicarAtividadeService.publicar(usuarioLogado.getUsername(), postRequest);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<PostFullResponse> listarPostsDosRelacionamentos(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarPostsMapper.apply(listarPostsDosRelacionamentosService.listar(usuarioLogado.getUsername()));
    }

    @GetMapping("/{email-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostFullResponse> listarPostsDoUsuario(@PathVariable("email-usuario") String email) {

        return listarPostsMapper.apply(listarPostDoUsuarioService.listar(email));
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<PostFullResponse> listarPostsDoUsuarioLogado(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarPostsMapper.apply(listarPostDoUsuarioEspecificoService.listar(usuarioLogado.getUsername()));
    }

    @PutMapping("/{post-id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@PathVariable("post-id") Long postId) {

        return atualizarPostParaEscondidoService.esconder(postId);
    }
}