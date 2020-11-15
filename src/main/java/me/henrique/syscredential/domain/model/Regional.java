package me.henrique.syscredential.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import me.henrique.syscredential.api.dto.RegionalInput;

@Entity
public class Regional {
	@Id
	private Integer id;
	private String nome;
	private String sigla;

	@JsonIgnore
	@OneToMany(mappedBy = "regional")
	private List<Participante> participantes = new ArrayList<>();

	public Regional() {
	}

	public Regional(RegionalInput dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.sigla = dto.getSigla();
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

}
