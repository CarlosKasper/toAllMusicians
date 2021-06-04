package br.com.cwi.crescer.tcc.moacyr.trombetta.controller;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request.UsuarioRequest;
import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.UsuarioResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Relacionamento;
import br.com.cwi.crescer.tcc.moacyr.trombetta.security.CustomUserDetails;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.CadastrarUsuarioService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento.ListarConvitesDeRelacionamentoPendentesService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.relacionamento.ListarRelacionamentosDoUsuarioService;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario.ListarUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

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
    public Page<Musico> listarUsuarios(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarUsuariosService.listar(usuarioLogado.getUsername(), pageable);
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/solicitacao")
    @ResponseStatus(HttpStatus.OK)
    public Page<Relacionamento> exibirConvites(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarConvitesDeRelacionamentoPendentesService.listar(usuarioLogado.getUsername(), pageable);

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/relacionamentos")
    @ResponseStatus(HttpStatus.OK)
    public Page<Relacionamento> exibirRelacionamentos(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarRelacionamentosDoUsuarioService.listar(usuarioLogado.getUsername(), pageable);

    }
}