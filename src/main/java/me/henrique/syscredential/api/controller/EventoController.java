package me.henrique.syscredential.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import me.henrique.syscredential.api.request.EventoRequest;
import me.henrique.syscredential.api.response.EventoResponse;
import me.henrique.syscredential.domain.enums.StatusEvento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	private GestaoEventoService service;

	@GetMapping
	public List<EventoResponse> getAll() {
		List<Evento> eventos = service.getAll();
		List<EventoResponse> eventosResponse = new ArrayList<>();
		eventos.forEach(evento -> eventosResponse.add(new EventoResponse(evento)));
		return eventosResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoResponse> getById(@PathVariable Integer id) {
		Evento evento = service.getById(id);
		return ResponseEntity.ok(new EventoResponse(evento));
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventoResponse> save(@Valid @RequestBody EventoRequest request) {
		Evento evento = new Evento(request);
		Evento eventoSalvo = service.save(evento);
		return ResponseEntity.ok(new EventoResponse(eventoSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventoResponse> update(@PathVariable Integer id, @RequestBody EventoRequest request) {
		Evento evento = new Evento(request);
		Evento eventoAtualizado = service.update(id, evento);
		return ResponseEntity.ok(new EventoResponse(eventoAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
