package me.henrique.syscredential.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.henrique.syscredential.api.dto.request.AtividadeRequest;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private LocalTime inicio;
	private LocalTime termino;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;

	public Atividade() {
	}

	public Atividade(AtividadeRequest dto) {
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.inicio = dto.getInicio();
		this.termino = dto.getTermino();
		this.evento = dto.getEvento();
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

	public LocalTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}

	public LocalTime getTermino() {
		return termino;
	}

	public void setTermino(LocalTime termino) {
		this.termino = termino;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
