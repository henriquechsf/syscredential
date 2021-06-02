package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Credenciamento;

import java.util.List;

public interface CredenciarParticipanteService {
    Credenciamento credenciarParticipante(Integer idEvento, String credencial);

    List<Credenciamento> listarParticipantesCredenciados(Integer idEvento);
}
