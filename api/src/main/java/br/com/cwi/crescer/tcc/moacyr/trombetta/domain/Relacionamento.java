package br.com.cwi.crescer.tcc.moacyr.trombetta.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "RELACIONAMENTO")
@SequenceGenerator(name = "SEQ_RELACIONAMENTO", sequenceName = "SEQ_RELACIONAMENTO", allocationSize = 1)
public class Relacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RELACIONAMENTO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO1")
    private Musico musico1;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO2")
    private Musico musico2;

    @Enumerated(value = EnumType.STRING)
    private Status status;
}
