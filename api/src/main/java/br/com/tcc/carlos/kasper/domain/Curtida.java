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
@Table(name = "CURTIDA")
@SequenceGenerator(name = "SEQ_CURTIDA", sequenceName = "SEQ_CURTIDA", allocationSize = 1)
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CURTIDA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDMUSICO")
    private Musico musico;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IDPOST")
    private Post post;
}