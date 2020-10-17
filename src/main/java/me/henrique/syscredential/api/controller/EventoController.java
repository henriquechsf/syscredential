package me.henrique.syscredential.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.henrique.syscredential.api.dto.EventoInput;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@CrossOrigin
	@GetMapping
	public List<Evento> listar() {
		return eventoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> listarPorId(@PathVariable Integer id) {
		Optional<Evento> evento = eventoRepository.findById(id);

		if (evento.isPresent()) {
			return ResponseEntity.ok(evento.get());
		}

		return ResponseEntity.notFound().build();
	}

	@CrossOrigin
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento adicionar(@Valid @RequestBody EventoInput dto) {
		Evento evento = new Evento(dto);
		return eventoRepository.save(evento);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody EventoInput dto) {
		if (!eventoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		Evento evento = new Evento(dto);

		return ResponseEntity.ok(evento);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		eventoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
