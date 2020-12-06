package me.henrique.syscredential.controller.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegionalRequest {

	@NotNull(message = "Código é obrigatório")
	private Integer cod;

	@NotNull(message = "Nome é obrigatório")
	@Length(min = 4, max = 50)
	private String nome;

	@NotNull(message = "Sigla é obrigatório")
	@Length(min = 2, max = 30)
	private String sigla;

	public RegionalRequest() {
	}

	public RegionalRequest(Integer cod, String nome, String sigla) {
		this.cod = cod;
		this.nome = nome;
		this.sigla = sigla;
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
