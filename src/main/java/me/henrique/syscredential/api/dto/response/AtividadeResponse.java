package me.henrique.syscredential.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtividadeResponse {

	private Integer id;
	private String titulo;
	private String descricao;

	@JsonFormat(pattern="HH:mm")
	private LocalTime inicio;

	@JsonFormat(pattern="HH:mm")
	private LocalTime termino;

}
