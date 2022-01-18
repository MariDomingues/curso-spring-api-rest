package com.curso.spring.model.enums;

public enum PerfilUsuario {

    USUARIO("ROLE_ALUNO"),
    MODERADOR("ROLE_MODERADOR");

    private String descricao;

    PerfilUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
