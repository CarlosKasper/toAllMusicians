package br.com.tcc.carlos.kasper.service.comentario;

import br.com.tcc.carlos.kasper.domain.Comentario;
import br.com.tcc.carlos.kasper.domain.Curtida;
import br.com.tcc.carlos.kasper.exception.ValidacaoNegocioException;
import br.com.tcc.carlos.kasper.repository.ComentarioRepository;
import br.com.tcc.carlos.kasper.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarComentarioPorIdService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario buscar(Long id) {

        Optional<Comentario> comentario = comentarioRepository.findById(id);

        return (comentario.get());
    }
}