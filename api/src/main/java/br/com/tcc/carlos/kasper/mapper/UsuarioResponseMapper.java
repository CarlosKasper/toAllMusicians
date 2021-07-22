package br.com.tcc.carlos.kasper.mapper;

import br.com.tcc.carlos.kasper.controller.response.UsuarioResponse;
import br.com.tcc.carlos.kasper.domain.Musico;
import org.springframework.stereotype.Component;

@Component
public class UsuarioResponseMapper {

    public UsuarioResponse apply(Musico musico) {

        UsuarioResponse response = new UsuarioResponse();

        response.setNome(musico.getNome());
        response.setDataNascimento(musico.getDataNascimento());
        response.setEmail(musico.getEmail());

        return response;
    }
}