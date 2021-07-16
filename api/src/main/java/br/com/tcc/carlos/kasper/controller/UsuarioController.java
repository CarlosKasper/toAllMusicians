package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.controller.request.UsuarioRequest;
import br.com.tcc.carlos.kasper.controller.response.UsuarioResponse;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Relacionamento;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorEmailService;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorNomeService;
import br.com.tcc.carlos.kasper.service.usuario.CadastrarUsuarioService;
import br.com.tcc.carlos.kasper.service.relacionamento.ListarConvitesDeRelacionamentoPendentesService;
import br.com.tcc.carlos.kasper.service.relacionamento.ListarRelacionamentosDoUsuarioService;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.service.usuario.ListarUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
    public List<Musico> listarUsuarios(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarUsuariosService.listar(usuarioLogado.getUsername());
    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/solicitacao")
    @ResponseStatus(HttpStatus.OK)
    public List<Relacionamento> exibirConvites(@AuthenticationPrincipal CustomUserDetails usuarioLogado, @PageableDefault Pageable pageable) {

        return listarConvitesDeRelacionamentoPendentesService.listar(usuarioLogado.getUsername());

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/relacionamentos/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Relacionamento> exibirRelacionamentos(@PathVariable("email") String email, @PageableDefault Pageable pageable) {

        return listarRelacionamentosDoUsuarioService.listar(email);

    }

    @RolesAllowed({"ROLE_USUARIO"})
    @GetMapping("/buscar/musico/{nome-usuario}")
    @ResponseStatus(HttpStatus.OK)
    public List<Musico> listarUsuarioPorNome(@PathVariable("nome-usuario") String nome, @PageableDefault Pageable pageable) {

        return buscarUsuarioPorNomeService.buscar(nome);

    }
}