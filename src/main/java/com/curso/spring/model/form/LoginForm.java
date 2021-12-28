package com.curso.spring.model.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

    private String usuario;
    private String senha;

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {

        return new UsernamePasswordAuthenticationToken(this.usuario, this.senha);
    }
}
