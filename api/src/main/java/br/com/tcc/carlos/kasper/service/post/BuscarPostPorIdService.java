package br.com.tcc.carlos.kasper.service.post;

import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.exception.RegistroNaoEncontradoException;
import br.com.tcc.carlos.kasper.repository.PostRepository;
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