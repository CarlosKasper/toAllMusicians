package br.com.cwi.crescer.tcc.moacyr.trombetta.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    PENDENTE,
    ACEITO,
    RECUSADO;
}
