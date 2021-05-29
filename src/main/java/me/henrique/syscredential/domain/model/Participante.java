package me.henrique.syscredential.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Participante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	
	@Enumerated(EnumType.STRING)
	private TamanhoCamiseta camiseta;

	private Boolean ativo;

	@ManyToOne
	@JoinColumn(name = "regional_id")
	private Regional regional;

	@OneToMany(mappedBy = "participante")
	private List<Credenciamento> credenciamentos;

}
