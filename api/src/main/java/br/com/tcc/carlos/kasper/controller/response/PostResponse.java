package br.com.tcc.carlos.kasper.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.CREATED)
@Data
public class PostResponse {

    private Long id;

    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataHoraPostagem;
}