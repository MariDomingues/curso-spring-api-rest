package com.curso.spring.model.form;

import com.curso.spring.model.entity.CursoEntity;
import com.curso.spring.model.entity.UsuarioEntity;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;

public class TopicoForm {

	@NotNull @NotEmpty
	private String titulo;

	@NotNull @NotEmpty
	private String mensagem;

	@NotNull @NotEmpty
	private UsuarioEntity autor;

	@NotNull @NotEmpty
	private String nomeCurso;

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

	public UsuarioEntity getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntity autor) {
		this.autor = autor;
	}

	public String getCurso() {
		return nomeCurso;
	}

	public void setCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
}
