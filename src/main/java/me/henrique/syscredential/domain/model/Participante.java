package me.henrique.syscredential.domain.model;

import me.henrique.syscredential.domain.enums.StatusParticipante;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private String urlFoto;
    private String tamanhoCamiseta;
    private StatusParticipante status;

    @ManyToOne
    private Departamento departamento;
    @ManyToOne
    private Regional regional;

    public Participante(String cpf, String nome, String email, Departamento departamento) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
