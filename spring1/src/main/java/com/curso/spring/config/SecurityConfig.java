package com.curso.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    //Configura a recursos estáticos - JS, CSS, imagens, etc
    public void configure(WebSecurity web) throws Exception {
    }

    @Override
    //Configura a autorização - URL, quem pode acessar cada URL, perfil de acesso
    protected void configure(HttpSecurity pHttp) throws Exception {

        pHttp.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topico").permitAll()
                .antMatchers(HttpMethod.GET, "/topico/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Override
    //Configura a autenticação - login
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }
}
