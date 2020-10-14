package me.henrique.syscredential.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Regional {
	@Id
	private Integer id;
	private String nome;
	private String sigla;

	public Regional() {
	}

	public Regional(Integer id, String nome, String sigla) {
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
