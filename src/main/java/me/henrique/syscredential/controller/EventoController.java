package me.henrique.syscredential.controller;

import me.henrique.syscredential.controller.request.EventoRequest;
import me.henrique.syscredential.controller.response.EventoResponse;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.services.GestaoEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private GestaoEventoService service;

	@GetMapping
	public List<EventoResponse> listar() {
		List<Evento> eventos = service.getAll();
		List<EventoResponse> eventosResponse = new ArrayList<>();
		eventos.forEach(evento -> eventosResponse.add(new EventoResponse(evento)));
		return eventosResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoResponse> listarPorId(@PathVariable Integer id) {
		Evento evento = service.getById(id);
		return ResponseEntity.ok(new EventoResponse(evento));
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventoResponse> adicionar(@Valid @RequestBody EventoRequest dto) {
		Evento evento = new Evento(dto);
		Evento eventoSalvo = service.save(evento);
		return ResponseEntity.ok(new EventoResponse(eventoSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventoResponse> atualizar(@PathVariable Integer id, @RequestBody EventoRequest dto) {
		Evento evento = new Evento(dto);
		Evento eventoAtualizado = service.update(id, evento);
		return ResponseEntity.ok(new EventoResponse(eventoAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
