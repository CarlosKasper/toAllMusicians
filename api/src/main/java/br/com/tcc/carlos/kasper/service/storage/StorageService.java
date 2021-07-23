package br.com.tcc.carlos.kasper.service.storage;

import br.com.tcc.carlos.kasper.domain.Imagem;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ImagemRepository;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorIdService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
@Service
public class StorageService {

    @Value("${cloud.aws.s3.url}")
    private String bucketUrl;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    public void uploadImagemPerfil(MultipartFile multipartFile, Long userId) {
        Imagem imagem = uploadImagem(multipartFile);
        Musico musico = buscarUsuarioPorIdService.buscar(userId);
        musico.setImagem(imagem);
        usuarioRepository.save(musico);
    }

    public void uploadImagemPost(MultipartFile multipartFile, Long postId) {
        Imagem imagem = uploadImagem(multipartFile);
        Post post = buscarPostPorIdService.buscar(postId);
        post.setImagem(imagem);
        postRepository.save(post);
    }

    private Imagem uploadImagem(MultipartFile multipartFile) {
        Imagem imagem = createNewImage();
        String fileName = profile.concat("/").concat(imagem.getId().toString());

        File file = convertMultiPartFileToFile(multipartFile);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
        file.delete();

        imagem.setUrl(bucketUrl.concat(fileName));
        return imagemRepository.save(imagem);
    }

    private Imagem createNewImage() {
        Imagem imagem = new Imagem();
        return imagemRepository.save(imagem);
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}