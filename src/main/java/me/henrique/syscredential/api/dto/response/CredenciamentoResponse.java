package me.henrique.syscredential.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;

import java.time.LocalDateTime;

public class CredenciamentoResponse {
    private Long id;
    private Integer idEvento;
    private String nomeEvento;

    //@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime instante;

    private Participante participante;

    @JsonIgnore
    private Evento evento;

    public CredenciamentoResponse() {
    }

    public CredenciamentoResponse(Credenciamento credenciamento) {
        this.id = credenciamento.getId();
        this.instante = credenciamento.getInstante();
        this.participante = credenciamento.getParticipante();
        this.evento = credenciamento.getEvento();
        this.idEvento = credenciamento.getEvento().getId();
        this.nomeEvento = credenciamento.getEvento().getTitulo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public void setInstante(LocalDateTime instante) {
        this.instante = instante;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
