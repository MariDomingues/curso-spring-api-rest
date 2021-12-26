package com.curso.spring.model.entity;

import com.curso.spring.model.enums.StatusTopico;
import com.curso.spring.model.form.TopicoForm;
import com.curso.spring.service.CursoService;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topico")
public class TopicoEntity {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

	@ManyToOne
	private UsuarioEntity autor;

	@ManyToOne
	private CursoEntity curso;

	@OneToMany(mappedBy = "topico")
	private List<RespostaEntity> vResposta = new ArrayList<>();

	public TopicoEntity() {
	}

	public TopicoEntity(TopicoForm pTopico, CursoService cursoService) {

		this.titulo = pTopico.getTitulo();
		this.mensagem = pTopico.getMensagem();
		this.autor = pTopico.getAutor();
		this.curso = cursoService.findByNome(pTopico.getCurso());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
	}

	public UsuarioEntity getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntity autor) {
		this.autor = autor;
	}

	public CursoEntity getCurso() {
		return curso;
	}

	public void setCurso(CursoEntity cursoEntity) {
		this.curso = cursoEntity;
	}

	public List<RespostaEntity> getRespostas() {
		return vResposta;
	}

	public void setRespostas(List<RespostaEntity> respostaEntities) {
		this.vResposta = respostaEntities;
	}

}
