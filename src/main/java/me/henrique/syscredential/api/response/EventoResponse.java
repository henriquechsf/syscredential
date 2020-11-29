package me.henrique.syscredential.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.henrique.syscredential.domain.enums.StatusEvento;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.model.Evento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoResponse {
    private Integer id;
    private String titulo;
    private String descricao;
    private String local;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime inicio;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDateTime termino;

    private StatusEvento status;

    public EventoResponse() {
    }

    public EventoResponse(Evento evento) {
        this.id = evento.getId();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
        this.local = evento.getLocal();
        this.inicio = evento.getInicio();
        this.termino = evento.getTermino();
        this.status = evento.getStatus();
    }

    private List<Atividade> atividades = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public StatusEvento getStatus() {
        return status;
    }

    public void setStatus(StatusEvento status) {
        this.status = status;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
}
