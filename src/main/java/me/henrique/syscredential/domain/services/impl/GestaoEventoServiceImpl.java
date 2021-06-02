package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.services.GestaoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoEventoServiceImpl implements GestaoEventoService {

	@Autowired
	private EventoRepository repository;

	@Override
	public List<Evento> getAll() {
		return repository.findAll();
	}

	@Override
	public Evento getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Override
	@Transactional
	public Evento save(Evento evento) {
		return repository.save(evento);
	}

	@Override
	@Transactional
	public Evento update(Integer id, Evento evento) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		evento.setId(id);

		return repository.save(evento);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
