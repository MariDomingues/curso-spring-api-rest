package com.curso.spring.model.dto;

import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.enums.StatusTopico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDetalheDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDto> vResposta = new ArrayList<>();

    public TopicoDetalheDto(TopicoEntity pTopico) {

        this.id = pTopico.getId();
        this.titulo = pTopico.getTitulo();
        this.mensagem = pTopico.getMensagem();
        this.dataCriacao = pTopico.getDataCriacao();
        this.nomeAutor = pTopico.getAutor().getNome();
        this.status = pTopico.getStatus();
        this.vResposta.addAll(pTopico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
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

    public StatusTopico getStatus() {
        return status;
    }

    public List<RespostaDto> getvResposta() {
        return vResposta;
    }
}
