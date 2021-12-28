package com.curso.spring.model.dto;

import com.curso.spring.model.entity.TopicoEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

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

	public static Page<TopicoDto> converter(Page<TopicoEntity> pListTopico) {

		return pListTopico.map(TopicoDto::new);
	}
}
