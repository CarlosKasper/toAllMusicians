package br.com.tcc.carlos.kasper.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMENTARIO")
@SequenceGenerator(name = "SEQ_COMENTARIO", sequenceName = "SEQ_COMENTARIO", allocationSize = 1)
public class Comentario implements Comparable<Comentario> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMENTARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO")
    private Musico musico;

    @ManyToOne
    @JoinColumn(name = "IDPOST")
    private Post post;

    @Column(name = "TEXTO")
    private String comentario;

    @Override
    public int compareTo(Comentario comentario) {
        if (this.id > (comentario.getId())) {
            return 1;
        } else {
            return -1;
        }
    }
}