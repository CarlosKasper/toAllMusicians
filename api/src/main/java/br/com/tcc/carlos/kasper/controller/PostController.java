package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.request.PostRequest;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.post.*;
import br.com.tcc.carlos.kasper.controller.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

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

    @PostMapping("/publicar")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse publicar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @RequestBody PostRequest postRequest) {

        return publicarAtividadeService.publicar(usuarioLogado.getUsername(), postRequest);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> listarPostsDosRelacionamentos(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault/*(direction = Sort.Direction.DESC, sort = "dataHoraPostagem")*/ Pageable pageable) {

        return listarPostsDosRelacionamentosService.listar(usuarioLogado.getUsername(), pageable);
    }

    @GetMapping("/listarTodos")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> listarTodos(@PageableDefault/*(direction = Sort.Direction.DESC, sort = "dataHoraPostagem")*/ Pageable pageable) {

        return listarTodosPosts.listar(pageable);
    }

    @GetMapping("/{email-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> listarPostsDoUsuario( @PathVariable("email-usuario") String email, @PageableDefault Pageable pageable){

        return listarPostDoUsuarioService.listar(email, pageable);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> listarPostsDoUsuarioLogado(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable){

        return listarPostDoUsuarioEspecificoService.listar(usuarioLogado.getUsername(), pageable);
    }

    @PutMapping("/{post-id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse updatePost(@PathVariable("post-id") Long postId){

        return atualizarPostParaEscondidoService.esconder(postId);
    }
}