package com.curso.spring.model.form;

import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.entity.UsuarioEntity;
import com.curso.spring.repository.TopicoRepository;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class TopicoUpdateForm {

	@NotNull @NotEmpty
	private String titulo;

	@NotNull @NotEmpty
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TopicoEntity update(Long pIdTopico, TopicoRepository pTopicoRepository) {

		TopicoEntity topico = pTopicoRepository.getById(pIdTopico);
		topico.setTitulo(this.getTitulo());
		topico.setMensagem(this.getMensagem());

		return topico;
	}
}
