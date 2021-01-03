package me.henrique.syscredential.controller;

import me.henrique.syscredential.controller.request.CredenciamentoRequest;
import me.henrique.syscredential.controller.request.EventoRequest;
import me.henrique.syscredential.controller.response.CredenciamentoResponse;
import me.henrique.syscredential.controller.response.EventoResponse;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.services.CredenciarParticipanteService;
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
	private GestaoEventoService eventoService;

	@Autowired
	private CredenciarParticipanteService credenciamentoService;

	@GetMapping
	public List<EventoResponse> listar() {
		List<Evento> eventos = eventoService.getAll();
		List<EventoResponse> eventosResponse = new ArrayList<>();
		eventos.forEach(evento -> eventosResponse.add(new EventoResponse(evento)));
		return eventosResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoResponse> listarPorId(@PathVariable Integer id) {
		Evento evento = eventoService.getById(id);
		return ResponseEntity.ok(new EventoResponse(evento));
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventoResponse> adicionar(@Valid @RequestBody EventoRequest dto) {
		Evento evento = new Evento(dto);
		Evento eventoSalvo = eventoService.save(evento);
		return ResponseEntity.ok(new EventoResponse(eventoSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventoResponse> atualizar(@PathVariable Integer id, @RequestBody EventoRequest dto) {
		Evento evento = new Evento(dto);
		Evento eventoAtualizado = eventoService.update(id, evento);
		return ResponseEntity.ok(new EventoResponse(eventoAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		eventoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{idEvento}/credenciamentos")
	public ResponseEntity<CredenciamentoResponse> credenciarParticipante(@PathVariable Integer idEvento, @RequestBody CredenciamentoRequest request) {
		Credenciamento credenciamento = credenciamentoService.credenciarParticipante(idEvento, request);
		return ResponseEntity.ok(new CredenciamentoResponse(credenciamento));
	}
}
