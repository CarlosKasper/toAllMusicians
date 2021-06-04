package br.com.cwi.crescer.tcc.moacyr.trombetta.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "MUSICO")
@SequenceGenerator(name = "SEQ_MUSICO", sequenceName = "SEQ_MUSICO", allocationSize = 1)
@EqualsAndHashCode(of = "email")
public class Musico {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MUSICO")
    private Long id;

    @Column(name = "NOME_COMPLETO")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "APELIDO")
    private String apelido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @JsonIgnore
    @Column(name = "SENHA")
    private String senha;

    @Column(name = "IMG_PERFIL_URL")
    private String imagem;

    @Enumerated(value = EnumType.STRING)
    private Instrumento instrumento;

    @JsonIgnore
    @Column(name = "ATIVO")
    private boolean ativo = true;

    @JsonIgnore
    @Enumerated(value = EnumType.STRING)
    private Perfil perfil;

    public Musico(String nome, String email, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
}


