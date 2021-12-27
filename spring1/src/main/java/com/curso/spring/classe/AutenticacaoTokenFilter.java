package com.curso.spring.classe;

import com.curso.spring.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//intercepta a requisição e executar a lógica antes que caia no controller
public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    public AutenticacaoTokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest pRequest,
                                    HttpServletResponse pResponse,
                                    FilterChain pFilterChain) throws ServletException, IOException {

        String token = recuperarToken(pRequest);
        boolean tokenValido = tokenService.isValid(token);

        pFilterChain.doFilter(pRequest, pResponse);
    }

    private String recuperarToken(HttpServletRequest pRequest) {

        String token = pRequest.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
