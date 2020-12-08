package me.henrique.syscredential.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String cidade;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime inicio;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime termino;

    private Boolean ativo;

    public EventoResponse() {
    }

    public EventoResponse(Evento evento) {
        this.id = evento.getId();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
        this.local = evento.getLocal();
        this.cidade = evento.getCidade();
        this.inicio = evento.getInicio();
        this.termino = evento.getTermino();
        this.ativo = evento.getAtivo();
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
