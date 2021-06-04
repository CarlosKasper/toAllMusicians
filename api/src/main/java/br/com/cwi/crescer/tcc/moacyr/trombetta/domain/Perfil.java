package br.com.cwi.crescer.tcc.moacyr.trombetta.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

    USUARIO("ROLE_USUARIO"),
    ADMINISTRADOR("ROLE_ADMINISTRADOR");

    private String role;
}
