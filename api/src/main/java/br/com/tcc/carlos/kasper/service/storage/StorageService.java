package br.com.tcc.carlos.kasper.service.storage;

import br.com.tcc.carlos.kasper.domain.Imagem;
import br.com.tcc.carlos.kasper.domain.Musico;
import br.com.tcc.carlos.kasper.domain.Post;
import br.com.tcc.carlos.kasper.repository.ImagemRepository;
import br.com.tcc.carlos.kasper.repository.UsuarioRepository;
import br.com.tcc.carlos.kasper.repository.PostRepository;
import br.com.tcc.carlos.kasper.service.post.BuscarPostPorIdService;
import br.com.tcc.carlos.kasper.service.usuario.BuscarUsuarioPorIdService;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    private BlobServiceClient blobServiceClient;

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
        File file = convertMultiPartFileToFile(multipartFile);
        Imagem imagem = createNewImage();
        String fileName = getFileName(file, imagem);
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(profile).getBlobClient(fileName);
        blobClient.uploadFromFile(file.getPath());

        imagem.setUrl(blobClient.getBlobUrl());

        file.delete();
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
            throw new RuntimeException("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }

    private String getFileName(File file, Imagem imagem) {
        String newFileName = imagem.getId().toString();
        String fileName = file.getName();
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return newFileName.concat(".").concat(extension);
        } else {
            return newFileName.concat(".jpg");
        }
    }
}