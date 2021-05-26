package me.henrique.syscredential.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void adicionarAtividade(List<Atividade> atividades) {
		atividades.forEach(atividade -> this.atividades.add(atividade));
	}
}
