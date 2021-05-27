package me.henrique.syscredential.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.model.Evento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeRequest {

	@NotNull(message = "Título é obrigatório")
	@Length(min = 4, max = 50)
	private String titulo;

	@NotNull(message = "Descrição é obrigatório")
	@Length(min = 10, max = 100)
	private String descricao;

	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Horário de início é obrigatório")
	private LocalTime inicio;

	@JsonFormat(pattern = "HH:mm")
	@NotNull(message = "Horário de encerramento é obrigatório")
	private LocalTime termino;

	private Evento evento;

}
