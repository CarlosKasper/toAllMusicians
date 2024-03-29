package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.response.InviteResponse;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.relacionamento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

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
    private ListarConvitesDeRelacionamentoPendentesService listarConvitesDeRelacionamentoPendentesService;

    @Autowired
    private RemoverAmizadeService removerAmizadeService;

    @GetMapping("/listar/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Musico> buscar(@PathVariable("email") String email) {
        return listarRelacionamentosDoUsuarioService.listar(email);
    }

    @GetMapping("/solicitacoes")
    @ResponseStatus(HttpStatus.OK)
    public List<InviteResponse> buscarSolicitacoes(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarConvitesDeRelacionamentoPendentesService.listar(usuarioLogado.getUsername());
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