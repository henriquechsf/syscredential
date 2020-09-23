package me.henrique.syscredential.domain.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;

    @ManyToOne
    private Evento evento;

    public Atividade(String titulo, String descricao, Date dataInicio, Date dataFim, Evento evento) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return id.equals(atividade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
