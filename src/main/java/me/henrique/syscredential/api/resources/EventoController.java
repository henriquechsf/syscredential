package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.EventoRequest;
import me.henrique.syscredential.api.dto.response.CredenciamentoResponse;
import me.henrique.syscredential.api.dto.response.EventoResponse;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.services.CredenciarParticipanteService;
import me.henrique.syscredential.domain.services.GestaoEventoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	private ModelMapper mapper;
	private GestaoEventoService eventoService;
	private CredenciarParticipanteService credenciamentoService;

	public EventoController(ModelMapper mapper, GestaoEventoService eventoService, CredenciarParticipanteService credenciamentoService) {
		this.mapper = mapper;
		this.eventoService = eventoService;
		this.credenciamentoService = credenciamentoService;
	}

	@GetMapping
	public List<EventoResponse> listar() {
		List<Evento> eventos = eventoService.getAll();
		List<EventoResponse> response = eventos
				.stream().map(evento -> mapper.map(evento, EventoResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<EventoResponse> listarPorId(@PathVariable Integer id) {
		Evento evento = eventoService.getById(id);
		EventoResponse response = mapper.map(evento, EventoResponse.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<EventoResponse> adicionar(@Valid @RequestBody EventoRequest request) {
		Evento evento = mapper.map(request, Evento.class);
		Evento eventoSalvo = eventoService.save(evento);
		EventoResponse response = mapper.map(eventoSalvo, EventoResponse.class);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EventoResponse> atualizar(@PathVariable Integer id, @Valid  @RequestBody EventoRequest request) {
		Evento evento = mapper.map(request, Evento.class);
		Evento eventoAtualizado = eventoService.update(id, evento);
		EventoResponse response = mapper.map(eventoAtualizado, EventoResponse.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		eventoService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// subrecurso de evento - CREDENCIAMENTO
	@GetMapping("/{idEvento}/credenciamentos")
	public ResponseEntity<List<CredenciamentoResponse>> listarParticipantesCredenciados(@PathVariable Integer idEvento) {
		List<Credenciamento> participantesCredenciados = credenciamentoService.listarParticipantesCredenciados(idEvento);

		List<CredenciamentoResponse> listaCredenciados = participantesCredenciados
				.stream()
				.map(credenciamento -> mapper.map(credenciamento, CredenciamentoResponse.class))
				.collect(Collectors.toList());

		return ResponseEntity.ok(listaCredenciados);
	}

	@PostMapping("/{idEvento}/credenciamentos/{credencial}")
	public ResponseEntity<CredenciamentoResponse> credenciarParticipante(@PathVariable Integer idEvento, @RequestBody @PathVariable String credencial) {
//		Credenciamento credenciamento = mapper.map(request, Credenciamento.class);
		Credenciamento credenciamentoSalvo = credenciamentoService.credenciarParticipante(idEvento, credencial);
		CredenciamentoResponse response = mapper.map(credenciamentoSalvo, CredenciamentoResponse.class);

		return ResponseEntity.ok(response);
	}
}
