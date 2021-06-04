package br.com.cwi.crescer.tcc.moacyr.trombetta.service.usuario;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request.UsuarioRequest;
import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.UsuarioResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Perfil;
import br.com.cwi.crescer.tcc.moacyr.trombetta.mapper.UsuarioResponseMapper;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastrarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidarEmailUsuarioService validarEmailUsuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioResponseMapper mapper;

    ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public UsuarioResponse cadastrar(UsuarioRequest usuarioRequest) {

        Musico musico = modelMapper.map(usuarioRequest, Musico.class);

        validarEmailUsuarioService.validarSeExistente(musico.getEmail());

        musico.setPerfil(Perfil.USUARIO);

        musico.setInstrumento(usuarioRequest.getInstrumento());

        musico.setSenha((usuarioRequest.getSenha()));

        usuarioRepository.save(musico);

        return mapper.apply(musico);
    }
}