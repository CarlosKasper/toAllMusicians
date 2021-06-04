package br.com.cwi.crescer.tcc.moacyr.trombetta.service.post;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Post;
import br.com.cwi.crescer.tcc.moacyr.trombetta.exception.RegistroNaoEncontradoException;
import br.com.cwi.crescer.tcc.moacyr.trombetta.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarPostPorIdService {

    @Autowired
    private PostRepository postRepository;

    public Post buscar(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            throw new RegistroNaoEncontradoException("Post não encontrado");
        }

        return (post.get());
    }
}
