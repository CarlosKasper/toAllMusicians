package br.com.cwi.crescer.tcc.moacyr.trombetta.controller;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.security.CustomUserDetails;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento.*;
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
@RequestMapping("/amizade")
public class RelacionamentoController {

    @Autowired
    private EnviarSolicitacaoDeRelacionamentoService enviarSolicitacaoDeRelacionamentoService;

    @Autowired
    private AceitarSolicitacaoDeRelacionamentoService aceitarSolicitacaoDeRelacionamentoService;

    @Autowired
    private RecusarSolicitacaoRelacionamentoService recusarSolicitacaoRelacionamentoService;

    @Autowired
    private ListarRelacionamentosDoUsuarioService listarRelacionamentosDoUsuarioService;

    @Autowired
    private RemoverAmizadeService removerAmizadeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Relacionamento> buscar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarRelacionamentosDoUsuarioService.listar(usuarioLogado.getUsername(), pageable);
    }

    @PostMapping("/{email-solicitado}")
    @ResponseStatus(HttpStatus.CREATED)
    public void solicitar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("email-solicitado") String solicitado) {

        enviarSolicitacaoDeRelacionamentoService.enviar(usuarioLogado.getUsername(), solicitado);
    }

    @PostMapping("/aceitar/{id-relacionamento}")
    @ResponseStatus(HttpStatus.CREATED)
    public void aceitar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-relacionamento") Long id) {

        aceitarSolicitacaoDeRelacionamentoService.aceitar(id, usuarioLogado.getUsername());
    }

    @PostMapping("/recusar/{id-relacionamento}")
    @ResponseStatus(HttpStatus.CREATED)
    public void recusar(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("id-relacionamento") Long id) {

        recusarSolicitacaoRelacionamentoService.recusar(id, usuarioLogado.getUsername());
    }

    @DeleteMapping("/remover/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void removerAmizade(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PathVariable("email") String email) {

        removerAmizadeService.remover(usuarioLogado.getUsername(), email);
    }
}