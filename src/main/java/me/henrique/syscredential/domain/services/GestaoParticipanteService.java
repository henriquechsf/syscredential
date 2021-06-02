package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Participante;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GestaoParticipanteService {
    List<Participante> getAll();

    Participante getById(Integer id);

    Participante getByCpf(String cpf);

    @Transactional
    Participante save(Participante participante);

    @Transactional
    Participante update(Integer id, Participante participante);

    @Transactional
    void delete(Integer id);
}
