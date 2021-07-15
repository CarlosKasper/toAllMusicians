package br.com.tcc.carlos.kasper.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "POST")
@SequenceGenerator(name = "SEQ_POST", sequenceName = "SEQ_POST", allocationSize = 1)
public class Post implements Comparable<Post> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_POST")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO")
    private Musico musico;

    @Column(name = "TITULO")
    private String titulo;

    @OneToOne
    @JoinColumn(name = "IDIMAGEM")
    private Imagem imagem;

    @Enumerated(value = EnumType.STRING)
    private Privacidade privacidade;

    @Enumerated(value = EnumType.STRING)
    private Instrumento instrumento;

    @Column(name = "DATA_HORA")
    private LocalDateTime dataHoraPostagem;

    public Post(Long id, String titulo, LocalDateTime dataHoraPostagem) {
        this.id = id;
        this.titulo = titulo;
        this.dataHoraPostagem = dataHoraPostagem;
    }

    @Override
    public int compareTo(Post post) {
        if (this.dataHoraPostagem.isAfter(post.getDataHoraPostagem())) {
            return -1;
        }
        if (this.dataHoraPostagem.isBefore(post.getDataHoraPostagem())) {
            return 1;
        }
        return 0;
    }
}