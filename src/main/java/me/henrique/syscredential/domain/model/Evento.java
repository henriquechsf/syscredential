package me.henrique.syscredential.domain.model;

import me.henrique.syscredential.domain.enums.StatusEvento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descricao;
    private String url;
    private Date dataInicio;
    private Date dataFim;
    private String urlBanner;
    private StatusEvento status;

    @OneToMany(mappedBy = "evento")
    private List<Atividade> atividades = new ArrayList<>();

    public Evento(String titulo, String descricao, Date dataInicio, Date dataFim) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id.equals(evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
