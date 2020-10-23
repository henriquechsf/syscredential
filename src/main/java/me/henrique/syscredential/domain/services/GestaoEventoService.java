package me.henrique.syscredential.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.RecursoNaoEncontradoException;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;

@Service
public class GestaoEventoService {

	@Autowired
	private EventoRepository eventoRepository;

	public List<Evento> listar() {
		return eventoRepository.findAll();
	}

	public Evento listarPorId(Integer id) {
		return eventoRepository.findById(id).orElseThrow(() -> new DomainException("ID não encontrado"));
	}

	public Evento salvar(Evento evento) {
		return eventoRepository.save(evento);
	}

	public Evento atualizar(Integer id, Evento evento) {
		if (!eventoRepository.existsById(id)) {
			throw new DomainException("ID não encontrado");
		}
		evento.setId(id);

		return eventoRepository.save(evento);
	}

	public void remover(Integer id) {
		if (!eventoRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("ID não encontrado");
		}
		eventoRepository.deleteById(id);
	}

}
