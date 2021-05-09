package me.henrique.syscredential.api.dto.request;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Regional;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ParticipanteRequest {

	//@CPF(message = "CPF inválido")
	@NotNull(message = "CPF é obrigatório")
	private String cpf;

	@NotNull(message = "Nome é obrigatório")
	@Length(min = 4, max = 50)
	private String nome;

	@Email
	private String email;

	private String telefone;

	private TamanhoCamiseta camiseta;

	private Boolean ativo = false;

	@NotNull(message = "Regional é obrigatório")
	private Regional regional;

	public ParticipanteRequest() {
	}

	public ParticipanteRequest(String cpf, String nome, String email, String telefone, TamanhoCamiseta camiseta, Boolean ativo,
							   Regional regional) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.camiseta = camiseta;
		this.ativo = ativo;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

}
