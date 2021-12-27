package com.curso.spring.service;

import com.curso.spring.model.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity autenticar(LoginForm pLogin) {

        UsernamePasswordAuthenticationToken dadosLogin = pLogin.converter();

        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerar(authenticate);

            return ResponseEntity.ok().build();

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();
        }
    }
}
