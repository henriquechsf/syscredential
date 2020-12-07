package me.henrique.syscredential.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class AtividadeRequest {

	@NotNull(message = "Título é obrigatório")
	@Length(min = 4, max = 50)
	private String titulo;

	@NotNull(message = "Descrição é obrigatório")
	@Length(min = 10, max = 100)
	private String descricao;

	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Horário de início é obrigatório")
	private LocalTime inicio;

	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Horário de encerramento é obrigatório")
	private LocalTime termino;

	public AtividadeRequest() {
	}

	public AtividadeRequest(String titulo, String descricao, LocalTime inicio, LocalTime termino) {
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

}
