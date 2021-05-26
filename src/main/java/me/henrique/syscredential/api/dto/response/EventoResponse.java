package me.henrique.syscredential.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoResponse {
    private Integer id;
    private String titulo;
    private String descricao;
    private String local;
    private String cidade;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime inicio;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime termino;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private LocalDateTime inicioFormatado;

    private Boolean ativo;
}
