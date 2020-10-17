package me.henrique.syscredential.api.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AtividadeInput {

	@NotNull(message = "Título é obrigatório")
	@NotBlank(message = "Informe o título")
	@Length(min = 4, max = 50)
	private String titulo;

	@NotNull(message = "Descrição é obrigatório")
	@NotBlank(message = "Informe a descrição")
	@Length(min = 10, max = 100)
	private String descricao;

	private Instant inicio;
	private Instant termino;

	public AtividadeInput() {
	}

	public AtividadeInput(String titulo, String descricao, Instant inicio, Instant termino) {
		this.titulo = titulo;
		this.descricao = descricao;
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
