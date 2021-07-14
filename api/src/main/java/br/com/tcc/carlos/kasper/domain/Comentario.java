package br.com.tcc.carlos.kasper.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "COMENTARIO")
@SequenceGenerator(name = "SEQ_COMENTARIO", sequenceName = "SEQ_COMENTARIO", allocationSize = 1)
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMENTARIO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO")
    private Musico musico;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IDPOST")
    private Post post;

    @Column(name = "TEXTO")
    private String comentario;
}
