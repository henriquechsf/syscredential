package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Transactional
	public Evento save(Evento evento) {
		return repository.save(evento);
	}

	@Transactional
	public Evento update(Integer id, Evento evento) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		evento.setId(id);

		return repository.save(evento);
	}

	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
