package me.henrique.syscredential.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Regional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipanteResponse {
	private Integer id;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private TamanhoCamiseta camiseta;
	private Boolean ativo;

	private Regional regional;
}
