package br.com.cwi.crescer.tcc.moacyr.trombetta.controller;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.security.CustomUserDetails;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida.CriarCurtidaService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.curtida.DeletarCurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RolesAllowed({"ROLE_USUARIO"})
@RestController
@RequestMapping("/curtida")
public class CurtidaController {

    @Autowired
    private CriarCurtidaService criarCurtidaService;

    @Autowired
    private DeletarCurtidaService deletarCurtidaService;

    @PostMapping("/{id-post}")
    @ResponseStatus(HttpStatus.CREATED)
    public void curtir(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-post") Long idPost) {

        criarCurtidaService.criar(usuarioLogado.getUsername(), idPost);
    }

    @DeleteMapping("/deletar/{id-post}/{id-curtida}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarCurtida(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-post") Long idPost, @PathVariable("id-curtida") Long idCurtida) {

        deletarCurtidaService.deletar(usuarioLogado.getUsername(), idPost, idCurtida);
    }
}