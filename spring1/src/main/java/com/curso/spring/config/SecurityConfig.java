package com.curso.spring.config;

import com.curso.spring.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticacaoService autenticacaoService;

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
                //Essa configuração evita que uma URL que não foi configurada seja pública.
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Override
    //Configura a autenticação - login
    protected void configure(AuthenticationManagerBuilder pAuth) throws Exception {

        pAuth.userDetailsService(autenticacaoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
