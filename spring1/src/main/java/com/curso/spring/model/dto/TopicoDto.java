package com.curso.spring.model.dto;

import com.curso.spring.model.entity.TopicoEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDto(TopicoEntity pTopico) {

		this.id = pTopico.getId();
		this.titulo = pTopico.getTitulo();
		this.mensagem = pTopico.getMensagem();
		this.dataCriacao = pTopico.getDataCriacao();
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

	public static List<TopicoDto> converter(List<TopicoEntity> pListTopico) {

		return pListTopico.stream().map(TopicoDto::new).collect(Collectors.toList());
	}
}
