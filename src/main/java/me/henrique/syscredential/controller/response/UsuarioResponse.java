package me.henrique.syscredential.controller.response;

import me.henrique.syscredential.domain.enums.Perfil;
import me.henrique.syscredential.domain.model.Usuario;

import java.util.HashSet;
import java.util.Set;

public class UsuarioResponse {
	private Integer id;
	private String nome;
	private String email;

	private Set<Perfil> perfis = new HashSet<>();

	public UsuarioResponse() {
	}

	public UsuarioResponse(Usuario dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.perfis = dto.getPerfis();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

}
