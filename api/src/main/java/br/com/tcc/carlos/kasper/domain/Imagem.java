package br.com.tcc.carlos.kasper.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "IMAGEM")
@SequenceGenerator(name = "SEQ_IMAGEM", sequenceName = "SEQ_IMAGEM", allocationSize = 1)
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMAGEM")
    private Long id;

    @Column(name = "URL")
    private String url;
}