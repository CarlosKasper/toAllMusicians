package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.curtida.CriarCurtidaService;
import br.com.tcc.carlos.kasper.service.curtida.DeletarCurtidaService;
import br.com.tcc.carlos.kasper.service.curtida.ListarCurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RolesAllowed({"ROLE_USUARIO"})
@RestController
@RequestMapping("/curtida")
public class CurtidaController {

    @Autowired
    private CriarCurtidaService criarCurtidaService;

    @Autowired
    private DeletarCurtidaService deletarCurtidaService;

    @Autowired
    private ListarCurtidaService listarCurtidaService;

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

    @GetMapping("/listar/{id-post}")
    @ResponseStatus(HttpStatus.OK)
    public List<Curtida> listarCurtida(@PathVariable("id-post") Long idPublicacao, @AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarCurtidaService.listar(idPublicacao, usuarioLogado.getUsername());
    }
}