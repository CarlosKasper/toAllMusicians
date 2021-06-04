package br.com.tcc.carlos.kasper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {

    USUARIO("ROLE_USUARIO"),
    ADMINISTRADOR("ROLE_ADMINISTRADOR");

    private String role;
}
