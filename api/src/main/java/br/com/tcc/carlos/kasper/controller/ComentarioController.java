package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.request.ComentarioRequest;
import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.comentario.CriarComentarioService;
import br.com.tcc.carlos.kasper.service.comentario.DeletarComentarioService;
import br.com.tcc.carlos.kasper.service.comentario.ListarComentarioService;
import br.com.tcc.carlos.kasper.service.curtida.CriarCurtidaService;
import br.com.tcc.carlos.kasper.service.curtida.DeletarCurtidaService;
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
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    private CriarComentarioService criarComentarioService;

    @Autowired
    private DeletarComentarioService deletarComentarioService;

    @Autowired
    private ListarComentarioService listarComentarioService;

    @PostMapping("/{id-post}")
    @ResponseStatus(HttpStatus.CREATED)
    public void comentar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-post") Long idPost, @RequestBody ComentarioRequest comentario) {

        criarComentarioService.criar(usuarioLogado.getUsername(), idPost, comentario.getComentario());
    }

    @DeleteMapping("/deletar/{id-post}/{id-comentario}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarComentario(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-post") Long idPost, @PathVariable("id-comentario") Long idComentario) {

        deletarComentarioService.deletar(usuarioLogado.getUsername(), idPost, idComentario);
    }

    @GetMapping("/listar/{id-post}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Comentario> listarComentario(@PathVariable("id-post") Long idPublicacao, @PageableDefault Pageable pageable, @AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarComentarioService.listar(idPublicacao, usuarioLogado.getUsername(), pageable);
    }
}