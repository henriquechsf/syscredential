package me.henrique.syscredential.domain.model;

import me.henrique.syscredential.controller.request.UsuarioRequest;
import me.henrique.syscredential.domain.enums.Perfil;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String email;
	private String senha;

	@Enumerated(EnumType.STRING)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Perfil> perfis = new HashSet<>();

	public Usuario() {
		addPerfil(Perfil.ROLE_USER);
	}

	public Usuario(UsuarioRequest dto) {
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.senha = dto.getSenha();
		addPerfil(Perfil.ROLE_USER);
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil);
	}
}