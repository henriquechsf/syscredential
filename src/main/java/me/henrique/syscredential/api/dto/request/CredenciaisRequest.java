package me.henrique.syscredential.api.dto.request;

import java.io.Serializable;

public class CredenciaisRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String senha;

    public CredenciaisRequest() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
