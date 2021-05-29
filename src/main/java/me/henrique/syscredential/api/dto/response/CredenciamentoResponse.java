package me.henrique.syscredential.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredenciamentoResponse {
    private Long id;
    private Integer idEvento;
    private String tituloEvento;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime instante;

    private Participante participante;

    @JsonIgnore
    private Evento evento;

}
