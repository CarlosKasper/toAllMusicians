package br.com.cwi.crescer.tcc.moacyr.trombetta.security;

import br.com.cwi.crescer.tcc.moacyr.trombetta.domain.Musico;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String senha;
    private String email;
    private boolean ativo;
    private List<SimpleGrantedAuthority> permissoes;

    public CustomUserDetails(Musico musico) {
        this.id = musico.getId();
        this.senha = musico.getSenha();
        this.email = musico.getEmail();
        this.ativo = musico.isAtivo();
        this.permissoes = asList(new SimpleGrantedAuthority(musico.getPerfil().getRole()));
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}