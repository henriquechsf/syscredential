package me.henrique.syscredential.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.enums.TamanhoCamiseta;
import me.henrique.syscredential.domain.model.Regional;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipanteRequest {

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

}
