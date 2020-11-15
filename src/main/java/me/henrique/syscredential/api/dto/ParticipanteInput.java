package me.henrique.syscredential.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Regional;

public class ParticipanteInput {

	@CPF(message = "CPF inválido")
	@NotNull(message = "CPF é obrigatório")
	private String cpf;

	@NotNull(message = "Nome é obrigatório")
	@NotBlank(message = "Informe um nome válido")
	@Length(min = 4, max = 50)
	private String nome;

	@Email
	@NotNull(message = "E-mail é obrigatório")
	private String email;
	private String telefone;

	@NotNull(message = "Camiseta é obrigatório")
	private TamanhoCamiseta camiseta;

	@NotNull(message = "Regional é obrigatório")
	private Regional regional;

	public ParticipanteInput() {
	}

	public ParticipanteInput(String cpf, String nome, String email, String telefone, TamanhoCamiseta camiseta,
			Regional regional) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.camiseta = camiseta;
		this.regional = regional;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public TamanhoCamiseta getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(TamanhoCamiseta camiseta) {
		this.camiseta = camiseta;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

}
