package me.henrique.syscredential.controller.response;

import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.model.Regional;

public class ParticipanteResponse {
	private Integer id;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private TamanhoCamiseta camiseta;
	private Boolean ativo;

	private Regional regional;

	public ParticipanteResponse() {
	}

	public ParticipanteResponse(Participante dto) {
		this.id = dto.getId();
		this.cpf = dto.getCpf();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.telefone = dto.getTelefone();
		this.camiseta = dto.getCamiseta();
		this.ativo = dto.getAtivo();
		this.regional = dto.getRegional();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
