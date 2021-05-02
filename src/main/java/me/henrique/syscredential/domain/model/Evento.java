package me.henrique.syscredential.domain.model;

import me.henrique.syscredential.api.dto.request.EventoRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private String local;
	private String cidade;
	private LocalDateTime inicio;
	private LocalDateTime termino;

	private Boolean ativo;

	@OneToMany(mappedBy = "evento")
	private List<Atividade> atividades = new ArrayList<>();

	@OneToMany(mappedBy = "evento")
	private List<Credenciamento> credenciamentos = new ArrayList<>();

	public Evento() {
	}

	public Evento(EventoRequest dto) {
		this.titulo = dto.getTitulo();
		this.descricao = dto.getDescricao();
		this.local = dto.getLocal();
		this.cidade = dto.getCidade();
		this.inicio = dto.getInicio();
		this.termino = dto.getTermino();
		this.ativo = dto.getAtivo();
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getTermino() {
		return termino;
	}

	public void setTermino(LocalDateTime termino) {
		this.termino = termino;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void adicionarAtividade(List<Atividade> atividades) {
		atividades.forEach(atividade -> this.atividades.add(atividade));
	}
}
