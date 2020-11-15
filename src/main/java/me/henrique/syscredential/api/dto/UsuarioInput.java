package me.henrique.syscredential.api.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UsuarioInput {

	@NotNull(message = "Login é obrigatório")
	@Length(min = 4, max = 30)
	private String login;

	@NotNull(message = "Senha é obrigatória")
	@Length(min = 4, max = 30)
	private String senha;

	public UsuarioInput() {
	}

	public UsuarioInput(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
