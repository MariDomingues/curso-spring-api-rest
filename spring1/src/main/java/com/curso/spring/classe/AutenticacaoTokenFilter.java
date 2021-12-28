package com.curso.spring.classe;

import com.curso.spring.model.entity.UsuarioEntity;
import com.curso.spring.service.TokenService;
import com.curso.spring.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//intercepta a requisição e executar a lógica antes que caia no controller
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    public AutenticacaoTokenFilter(TokenService tokenService, UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest pRequest,
                                    HttpServletResponse pResponse,
                                    FilterChain pFilterChain) throws ServletException, IOException {

        String token = recuperarToken(pRequest);
        boolean tokenValido = tokenService.isValid(token);

        if (tokenValido) {

            autenticarCliente(token);
        }

        pFilterChain.doFilter(pRequest, pResponse);
    }

    private void autenticarCliente(String pToken) {

        long idUsuario = tokenService.getIdUsuario(pToken);
        UsuarioEntity usuario = usuarioService.getUsuario(idUsuario);

        UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

        //autentica o usuário
        SecurityContextHolder.getContext().setAuthentication(autenticacao);
    }

    private String recuperarToken(HttpServletRequest pRequest) {

        String token = pRequest.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
