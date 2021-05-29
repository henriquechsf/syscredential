package me.henrique.syscredential.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.henrique.syscredential.domain.model.Atividade;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoRequest {

    @NotNull(message = "Título é obrigatório")
    @Length(min = 4, max = 50)
    private String titulo;

    @NotNull(message = "Descrição é obrigatório")
    @Length(min = 10, max = 100)
    private String descricao;

    @NotNull(message = "Local é obrigatório")
    @Length(min = 4, max = 50)
    private String local;

    @NotNull(message = "Local é obrigatório")
    @Length(min = 4, max = 50)
    private String cidade;

    @FutureOrPresent(message = "Não é permitido datas passadas")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime inicio;

    @FutureOrPresent(message = "Não é permitido datas passadas")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime termino;

    private Boolean ativo = false;

    private List<Atividade> atividades = new ArrayList<>();

}
