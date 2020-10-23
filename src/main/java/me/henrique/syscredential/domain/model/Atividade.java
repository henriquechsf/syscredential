package me.henrique.syscredential.domain.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import me.henrique.syscredential.api.dto.AtividadeInput;

@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private Instant inicio;
	private Instant termino;

	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;

	public Atividade() {
	}

	public Atividade(AtividadeInput dto) {
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.inicio = dto.getInicio();
		this.termino = dto.getTermino();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Instant getInicio() {
		return inicio;
	}

	public void setInicio(Instant inicio) {
		this.inicio = inicio;
	}

	public Instant getTermino() {
		return termino;
	}

	public void setTermino(Instant termino) {
		this.termino = termino;
	}

}
