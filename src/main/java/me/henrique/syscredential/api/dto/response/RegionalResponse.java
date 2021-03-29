package me.henrique.syscredential.api.dto.response;

import me.henrique.syscredential.domain.model.Regional;

public class RegionalResponse {
	private Integer id;
	private Integer cod;
	private String nome;
	private String sigla;

	public RegionalResponse() {
	}

	public RegionalResponse(Regional dto) {
		this.id = dto.getId();
		this.cod = dto.getCod();
		this.nome = dto.getNome();
		this.sigla = dto.getSigla();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
