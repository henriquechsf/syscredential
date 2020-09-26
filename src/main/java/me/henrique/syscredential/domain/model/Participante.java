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

    public Participante() {
    }

    public Participante(Integer id, String cpf, String nome, String email, String telefone, String urlFoto, String tamanhoCamiseta, StatusParticipante status, Departamento departamento, Regional regional) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.urlFoto = urlFoto;
        this.tamanhoCamiseta = tamanhoCamiseta;
        this.status = status;
        this.departamento = departamento;
        this.regional = regional;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getTamanhoCamiseta() {
        return tamanhoCamiseta;
    }

    public void setTamanhoCamiseta(String tamanhoCamiseta) {
        this.tamanhoCamiseta = tamanhoCamiseta;
    }

    public StatusParticipante getStatus() {
        return status;
    }

    public void setStatus(StatusParticipante status) {
        this.status = status;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
