package br.com.tcc.carlos.kasper.service.usuario;

import br.com.tcc.carlos.kasper.controller.request.UsuarioRequest;
import br.com.tcc.carlos.kasper.controller.response.UsuarioResponse;
import br.com.tcc.carlos.kasper.domain.Instrumento;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.domain.Privacidade;
import br.com.tcc.carlos.kasper.exception.RegistroNaoEncontradoException;
import br.com.tcc.carlos.kasper.mapper.UsuarioResponseMapper;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UpdateUserInformationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    private UsuarioResponseMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponse update(CustomUserDetails usuarioLogado, UsuarioRequest usuarioRequest) {

        Musico musico = buscarUsuarioPorEmailService.buscar(usuarioLogado.getUsername());

        Optional<Musico> musicoEmailNovoCheck = usuarioRepository.findByEmail(usuarioRequest.getEmail());

        if(!musicoEmailNovoCheck.isPresent() || musico.getEmail().equals(usuarioRequest.getEmail())) {
            musico.setEmail(usuarioRequest.getEmail());
            musico.setNome(usuarioRequest.getNome());
            musico.setSenha(passwordEncoder.encode(usuarioRequest.getSenha()));
            musico.setApelido(usuarioRequest.getApelido());
            musico.setDataNascimento(usuarioRequest.getDataNascimento());
            musico.setInstrumento(usuarioRequest.getInstrumento());

            usuarioRepository.save(musico);

            return mapper.apply(musico);
        }
        return null;
    }
}
