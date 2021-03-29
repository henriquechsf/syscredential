package me.henrique.syscredential.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.henrique.syscredential.domain.model.Atividade;

import java.time.LocalTime;

public class AtividadeResponse {

	private Integer id;
	private String titulo;
	private String descricao;

	@JsonFormat(pattern="HH:mm")
	private LocalTime inicio;

	@JsonFormat(pattern="HH:mm")
	private LocalTime termino;

	public AtividadeResponse() {
	}

	public AtividadeResponse(Atividade atividade) {
		this.id = atividade.getId();
		this.titulo = atividade.getTitulo();
		this.descricao = atividade.getDescricao();
		this.inicio = atividade.getInicio();
		this.termino = atividade.getTermino();
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

}
