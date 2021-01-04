package me.henrique.syscredential.controller.request;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class UsuarioRequest {
	@NotNull(message = "Nome é obrigatório")
	@Length(min = 4, max = 30)
	private String nome;

	@NotNull(message = "E-mail é obrigatório")
	@Length(min = 4, max = 30)
	private String email;

	@NotNull(message = "Senha é obrigatória")
	@Length(min = 4, max = 30)
	private String senha;

	public UsuarioRequest() {
	}

	public UsuarioRequest(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
