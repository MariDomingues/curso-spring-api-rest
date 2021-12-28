package com.curso.spring.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resposta")
public class RespostaEntity {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String mensagem;

	@ManyToOne
	private TopicoEntity topico;
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@ManyToOne
	private UsuarioEntity autor;
	private Boolean solucao = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TopicoEntity getTopico() {
		return topico;
	}

	public void setTopico(TopicoEntity topicoEntity) {
		this.topico = topicoEntity;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public UsuarioEntity getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntity autor) {
		this.autor = autor;
	}

	public Boolean getSolucao() {
		return solucao;
	}

	public void setSolucao(Boolean solucao) {
		this.solucao = solucao;
	}
}
