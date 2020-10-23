package me.henrique.syscredential.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RegionalInput {

	@NotNull(message = "ID é obrigatório")
	private Integer id;

	@NotNull(message = "Nome é obrigatório")
	@NotBlank(message = "Informe um nome válido")
	@Length(min = 4, max = 50)
	private String nome;

	@NotNull(message = "Sigla é obrigatório")
	@NotBlank(message = "Informe uma sigla válida")
	@Length(min = 2, max = 30)
	private String sigla;

	public RegionalInput() {
	}

	public RegionalInput(Integer id, String nome, String sigla) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
