package br.com.tcc.carlos.kasper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Privacidade {
    PUBLICO,
    PRIVADO,
    ESCONDIDO
}