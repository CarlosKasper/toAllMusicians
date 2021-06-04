package br.com.tcc.carlos.kasper.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CURTIDA")
@SequenceGenerator(name = "SEQ_CURTIDA", sequenceName = "SEQ_CURTIDA", allocationSize = 1)
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURTIDA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO")
    private Musico musico;

    @ManyToOne
    @JoinColumn(name = "IDPOST")
    private Post post;
}