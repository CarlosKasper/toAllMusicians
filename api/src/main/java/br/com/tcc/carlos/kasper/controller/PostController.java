package br.com.cwi.crescer.tcc.moacyr.trombetta.controller;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request.PostRequest;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.security.CustomUserDetails;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.post.PublicarPostService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.PostResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.post.ListarPostDoUsuarioService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.post.ListarPostsDosRelacionamentosService;
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

    @GetMapping("/{email-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Post> listarPostsDoUsuario(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("email-usuario") String email, @PageableDefault Pageable pageable){

        return listarPostDoUsuarioService.listar(email, pageable);
    }
}