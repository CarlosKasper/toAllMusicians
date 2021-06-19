package br.com.tcc.carlos.kasper.controller;

import br.com.tcc.carlos.kasper.security.CustomUserDetails;
import br.com.tcc.carlos.kasper.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagem")
public class ImagemController {

    private static final String UPLOAD_ERRO = "Erro ao executar o upload da imagem";

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload/perfil")
    public ResponseEntity<String> uploadImagemPerfil(@AuthenticationPrincipal CustomUserDetails user, @RequestParam(value = "image") MultipartFile file) {
        try {
            storageService.uploadImagemPerfil(file, user.getId());
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(UPLOAD_ERRO, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload/post")
    public ResponseEntity<String> uploadImagemPost(@RequestParam(value = "postId") Long postId, @RequestParam(value = "image") MultipartFile file) {
        try {
            storageService.uploadImagemPost(file, postId);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(UPLOAD_ERRO, HttpStatus.BAD_REQUEST);
        }
    }
}