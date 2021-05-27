package me.henrique.syscredential.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Atividade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;
	private String descricao;
	private LocalTime inicio;
	private LocalTime termino;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "evento_id")
	private Evento evento;

}
