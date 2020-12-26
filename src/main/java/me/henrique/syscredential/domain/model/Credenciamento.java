package me.henrique.syscredential.domain.model;

import java.time.LocalDateTime;

public class Credenciamento {
    private LocalDateTime instante;
    private Participante participante;
    private Evento evento;

    public Credenciamento(LocalDateTime instante, Participante participante, Evento evento) {
        this.instante = instante;
        this.participante = participante;
        this.evento = evento;
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
}
