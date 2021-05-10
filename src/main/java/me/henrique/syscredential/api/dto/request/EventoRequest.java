package me.henrique.syscredential.api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.henrique.syscredential.domain.model.Atividade;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public EventoRequest() {
    }

    public EventoRequest(String titulo, String descricao, String local, String cidade, LocalDateTime inicio, LocalDateTime termino, Boolean ativo) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.cidade = cidade;
        this.inicio = inicio;
        this.termino = termino;
        this.ativo = ativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getTermino() {
        return termino;
    }

    public void setTermino(LocalDateTime termino) {
        this.termino = termino;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
