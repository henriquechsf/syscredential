package me.henrique.syscredential.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegionalResponse {
	private Integer id;
	private Integer cod;
	private String nome;
	private String sigla;
}
