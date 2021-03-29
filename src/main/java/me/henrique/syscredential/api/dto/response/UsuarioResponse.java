package me.henrique.syscredential.api.dto.response;

import me.henrique.syscredential.domain.enums.Perfil;
import me.henrique.syscredential.domain.model.Usuario;

import java.util.HashSet;
import java.util.Set;

public class UsuarioResponse {
	private Integer id;
	private String nome;
	private String login;

	private Set<Perfil> perfis = new HashSet<>();

	public UsuarioResponse() {
	}

	public UsuarioResponse(Usuario dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.login = dto.getLogin();
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

}
