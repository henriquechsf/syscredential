package me.henrique.syscredential.api.controller;

import java.util.List;

import javax.transaction.Transactional;
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
import me.henrique.syscredential.domain.services.GestaoEventoService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private GestaoEventoService service;

	@GetMapping
	public List<Evento> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Evento> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.listarPorId(id));
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Evento> adicionar(@Valid @RequestBody EventoInput dto) {
		Evento evento = new Evento(dto);
		return ResponseEntity.ok(service.salvar(evento));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Evento> atualizar(@PathVariable Integer id, @RequestBody EventoInput dto) {
		Evento evento = new Evento(dto);
		return ResponseEntity.ok(service.atualizar(id, evento));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}
