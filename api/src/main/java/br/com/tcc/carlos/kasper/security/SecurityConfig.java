package br.com.tcc.carlos.kasper.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true

)
public class SecurityConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http

                .headers().frameOptions().sameOrigin()
                .and()
                .cors().and().csrf().disable()

                // definir regras abertas
                .authorizeRequests()
                .antMatchers("/usuario/cadastro").permitAll()

                // Somente quem é admin pode acessar
                .antMatchers("/admin").hasRole("ADMINISTRADOR")

                // o resto é fechado
                .anyRequest().authenticated();
    }
}
