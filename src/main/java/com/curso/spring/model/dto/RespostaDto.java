package com.curso.spring.model.dto;

import com.curso.spring.model.entity.RespostaEntity;

import java.time.LocalDateTime;

public class RespostaDto {

    private long id;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;

    public RespostaDto(RespostaEntity pResposta) {

        this.id = pResposta.getId();
        this.mensagem = pResposta.getMensagem();
        this.dataCriacao = pResposta.getDataCriacao();
        this.nomeAutor = pResposta.getAutor().getNome();
    }

    public long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }
}
