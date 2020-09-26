package me.henrique.syscredential.api.controller;

import me.henrique.syscredential.api.dto.EventoFormDto;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Evento> listar() {
        return repository.findAll();
    }


    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Evento cadastrar(@RequestBody Evento evento) {
        return repository.save(evento);
    }
}
