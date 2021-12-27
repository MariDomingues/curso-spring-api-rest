package com.curso.spring.service;

import com.curso.spring.model.dto.TokenDto;
import com.curso.spring.model.entity.UsuarioEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    //busca o parâmetro que foi colocado na application.properties
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public TokenDto gerar(Authentication pAuthenticate) {

        UsuarioEntity usuario = (UsuarioEntity) pAuthenticate.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return new TokenDto(Jwts.builder()
                .setIssuer("API Fórum Alura")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact(), "Bearer");
    }
}