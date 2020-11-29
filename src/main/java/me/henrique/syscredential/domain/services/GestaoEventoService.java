package me.henrique.syscredential.domain.services;

import java.util.List;

import me.henrique.syscredential.domain.enums.StatusEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;

@Service
public class GestaoEventoService {

	@Autowired
	private EventoRepository repository;

	public List<Evento> getAll() {
		return repository.findAll();
	}

	public Evento getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	public Evento save(Evento evento) {
		return repository.save(evento);
	}

	public Evento update(Integer id, Evento evento) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		evento.setId(id);

		return repository.save(evento);
	}

	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
