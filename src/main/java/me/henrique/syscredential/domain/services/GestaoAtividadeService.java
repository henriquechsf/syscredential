package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Atividade;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GestaoAtividadeService {
    List<Atividade> getAll();

    Atividade getById(Integer id);

    @Transactional
    Atividade save(Atividade atividade);

    @Transactional
    Atividade update(Integer id, Atividade atividade);

    @Transactional
    void delete(Integer id);
}
