package br.com.cwi.crescer.tcc.moacyr.trombetta.mapper;

import br.com.cwi.crescer.tcc.moacyr.trombetta.controller.response.UsuarioResponse;
import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
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
