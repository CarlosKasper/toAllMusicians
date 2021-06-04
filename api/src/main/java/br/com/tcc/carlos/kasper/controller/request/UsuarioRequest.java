package br.com.cwi.crescer.tcc.moacyr.trombetta.controller.request;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Instrumento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotEmpty
    private String nome;

    @Email
    @NotEmpty
    private String email;

    private String apelido;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotEmpty
    private String senha;

    private String imagem;

    private Instrumento instrumento;
}