package me.henrique.syscredential.api.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class EventoInput {

	@NotNull(message = "Título é obrigatório")
	@Length(min = 4, max = 50)
	private String titulo;

	@NotNull(message = "Descrição é obrigatório")
	@Length(min = 10, max = 100)
	private String descricao;

	@NotNull(message = "Local é obrigatório")
	@Length(min = 4, max = 50)
	private String local;

	@FutureOrPresent
	private LocalDateTime inicio;

	@FutureOrPresent
	private LocalDateTime termino;

	public EventoInput() {
	}

	public EventoInput(String titulo, String descricao, String local, LocalDateTime inicio, LocalDateTime termino) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.local = local;
		this.inicio = inicio;
		this.termino = termino;
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

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

}
