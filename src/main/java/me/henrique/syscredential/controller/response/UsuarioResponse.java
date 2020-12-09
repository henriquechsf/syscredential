package me.henrique.syscredential.controller.response;

import me.henrique.syscredential.domain.model.Usuario;

public class UsuarioResponse {
	private Integer id;
	private String nome;
	private String login;
	private Boolean admin;

	public UsuarioResponse() {
	}

	public UsuarioResponse(Usuario dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.login = dto.getLogin();
		this.admin = dto.getAdmin();
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

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
