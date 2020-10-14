package me.henrique.syscredential.domain.model;

import java.util.Date;

import javax.persistence.*;

import me.henrique.syscredential.domain.enums.StatusEvento;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private String local;
	private Date inicio;
	private Date termino;
	private StatusEvento status;

	public Evento() {
	}

	public Evento(String titulo, String descricao, String local, Date inicio, Date termino) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.local = local;
		this.inicio = inicio;
		this.termino = termino;
		this.status = StatusEvento.CADASTRADO;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public StatusEvento getStatus() {
		return status;
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
	}

}
