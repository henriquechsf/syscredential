package me.henrique.syscredential.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import me.henrique.syscredential.api.dto.ParticipanteInput;
import me.henrique.syscredential.domain.enums.StatusParticipante;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;

@Entity
public class Participante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private TamanhoCamiseta camiseta;
	private StatusParticipante status;

	@ManyToOne
	@JoinColumn(name = "regional_id")
	private Regional regional;

	public Participante() {
	}

	public Participante(ParticipanteInput dto) {
		this.cpf = dto.getCpf();
		this.nome = dto.getNome();
		this.email = dto.getEmail();
		this.telefone = dto.getTelefone();
		this.camiseta = dto.getCamiseta();
		this.regional = dto.getRegional();
		this.status = StatusParticipante.CADASTRADO;
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

	public StatusParticipante getStatus() {
		return status;
	}

	public void setStatus(StatusParticipante status) {
		this.status = status;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

}
