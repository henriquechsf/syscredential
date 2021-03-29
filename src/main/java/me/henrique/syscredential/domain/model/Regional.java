package me.henrique.syscredential.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import me.henrique.syscredential.api.dto.request.RegionalRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Regional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private Integer cod;

	private String nome;
	private String sigla;

	@JsonIgnore
	@OneToMany(mappedBy = "regional")
	private List<Participante> participantes = new ArrayList<>();

	public Regional() {
	}

	public Regional(RegionalRequest dto) {
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

	public List<Participante> getParticipantes() {
		return participantes;
	}

}
