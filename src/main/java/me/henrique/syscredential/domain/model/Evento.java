package me.henrique.syscredential.domain.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import me.henrique.syscredential.api.dto.EventoInput;
import me.henrique.syscredential.domain.enums.StatusEvento;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private String local;
	private Instant inicio;
	private Instant termino;
	private StatusEvento status;
	
	@OneToMany(mappedBy = "evento")
	private List<Atividade> atividades = new ArrayList<>();

	public Evento() {
	}

	public Evento(EventoInput dto) {
		this.titulo = 		dto.getTitulo();
		this.descricao = 	dto.getDescricao();
		this.local = 		dto.getLocal();
		this.inicio = 		dto.getInicio();
		this.termino = 		dto.getTermino();
		this.status = StatusEvento.CADASTRADO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Instant getInicio() {
		return inicio;
	}

	public void setInicio(Instant inicio) {
		this.inicio = inicio;
	}

	public Instant getTermino() {
		return termino;
	}

	public void setTermino(Instant termino) {
		this.termino = termino;
	}

	public StatusEvento getStatus() {
		return status;
	}

	public void setStatus(StatusEvento status) {
		this.status = status;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void adicionarAtividade(Atividade atividade) {
		this.atividades.add(atividade);
	}
}
