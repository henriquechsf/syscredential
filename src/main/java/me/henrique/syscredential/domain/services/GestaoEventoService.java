package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Evento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GestaoEventoService {
    List<Evento> getAll();

    Evento getById(Integer id);

    @Transactional
    Evento save(Evento evento);

    @Transactional
    Evento update(Integer id, Evento evento);

    @Transactional
    void delete(Integer id);
}
