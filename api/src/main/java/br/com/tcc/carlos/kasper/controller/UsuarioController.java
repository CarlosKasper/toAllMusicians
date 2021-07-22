package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.request.ComentarioRequest;
import br.com.tcc.carlos.kasper.controller.request.UsuarioRequest;
import br.com.tcc.carlos.kasper.controller.response.UsuarioResponse;
import br.com.tcc.carlos.kasper.domain.Instrumento;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.usuario.*;
import br.com.tcc.carlos.kasper.service.relacionamento.ListarConvitesDeRelacionamentoPendentesService;
import br.com.tcc.carlos.kasper.service.relacionamento.ListarRelacionamentosDoUsuarioService;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private ListarConvitesDeRelacionamentoPendentesService listarConvitesDeRelacionamentoPendentesService;

    @Autowired
    private ListarRelacionamentosDoUsuarioService listarRelacionamentosDoUsuarioService;

    @Autowired
    private ListarUsuariosService listarUsuariosService;

    @Autowired
    private BuscarUsuarioPorNomeService buscarUsuarioPorNomeService;

    @Autowired
    private UpdateUserInformationService updateUserInformationService;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse cadastrar(@RequestBody UsuarioRequest usuarioRequest) {

        return cadastrarUsuarioService.cadastrar(usuarioRequest);
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Musico exibirDadosUsuario(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return buscarUsuarioPorEmailService.buscar(usuarioLogado.getUsername());
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/buscar/{email-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public Musico exibirDadosDoPefilDoUsuario(@PathVariable("email-usuario") String email) {

        return buscarUsuarioPorEmailService.buscar(email);
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Musico> listarUsuarios(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarUsuariosService.listar(usuarioLogado.getUsername());
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/solicitacao")
    @ResponseStatus(HttpStatus.OK)
    public List<Relacionamento> exibirConvites(@AuthenticationPrincipal CustomUserDetails usuarioLogado) {

        return listarConvitesDeRelacionamentoPendentesService.listar(usuarioLogado.getUsername());

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/relacionamentos/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Musico> exibirRelacionamentos(@PathVariable("email") String email) {

        return listarRelacionamentosDoUsuarioService.listar(email);

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/buscar/musico/{nome-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public List<Musico> listarUsuarioPorNome(@PathVariable("nome-usuario") String nome) {

        return buscarUsuarioPorNomeService.buscar(nome);

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponse exibirDadosDoPefilDoUsuario(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @RequestBody UsuarioRequest usuarioRequest) {

        return updateUserInformationService.update(usuarioLogado, usuarioRequest);
    }


}