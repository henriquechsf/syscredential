package me.henrique.syscredential.controller.request;

public class CredenciamentoRequest {
    private String cpf;

    public CredenciamentoRequest() {
    }

    public CredenciamentoRequest(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
