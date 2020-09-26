package me.henrique.syscredential.domain.model;

import me.henrique.syscredential.domain.enums.TipoRegional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Regional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sigla;
    private TipoRegional tipo;

    public Regional() {
    }

    public Regional(Integer id, String nome, String sigla, TipoRegional tipo) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public TipoRegional getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegional tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regional regional = (Regional) o;
        return Objects.equals(id, regional.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
