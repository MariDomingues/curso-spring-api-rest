package com.curso.spring.config;

import com.curso.spring.classe.AutenticacaoTokenFilter;
import com.curso.spring.service.TokenService;
import com.curso.spring.service.UserDetailService;
import com.curso.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    //Configura a recursos estáticos - JS, CSS, imagens, etc
    public void configure(WebSecurity pWeb) throws Exception {

        pWeb.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }

    @Override
    //Configura a autorização - URL, quem pode acessar cada URL, perfil de acesso
    protected void configure(HttpSecurity pHttp) throws Exception {

        pHttp.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topico").permitAll()
                .antMatchers(HttpMethod.GET, "/topico/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                //Essa configuração evita que uma URL que não foi configurada seja pública.
                .anyRequest().authenticated()
                .and().csrf().disable()
                //não cria uma sessão
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoTokenFilter(tokenService, usuarioService), UsernamePasswordAuthenticationFilter.class);
                //gerar automaticamente um formulário de login
                //.and().formLogin();
    }

    @Override
    //Configura a autenticação - login
    protected void configure(AuthenticationManagerBuilder pAuth) throws Exception {

        pAuth.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
