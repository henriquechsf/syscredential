package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.ParticipanteRequest;
import me.henrique.syscredential.api.dto.response.ParticipanteResponse;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.services.GestaoParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	@Autowired
	private GestaoParticipanteService service;

	@GetMapping
	public List<ParticipanteResponse> listar() {
		List<Participante> participantes = service.getAll();
		List<ParticipanteResponse> participantesResponse = new ArrayList<>();
		participantes.forEach(participante -> participantesResponse.add(new ParticipanteResponse(participante)));
		return participantesResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticipanteResponse> listarPorId(@PathVariable Integer id) {
		Participante participante = service.getById(id);
		return ResponseEntity.ok(new ParticipanteResponse(participante));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ParticipanteResponse> listarPorCpf(@PathVariable String cpf) {
		Participante participante = service.getByCpf(cpf);
		return ResponseEntity.ok(new ParticipanteResponse(participante));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParticipanteResponse> adicionar(@Valid @RequestBody ParticipanteRequest dto) {
		Participante participante = new Participante(dto);
		Participante participanteSalvo = service.save(participante);
		return ResponseEntity.ok(new ParticipanteResponse(participanteSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticipanteResponse> atualizar(@PathVariable Integer id, @RequestBody ParticipanteRequest dto) {
		Participante participante = new Participante(dto);
		Participante participanteAtualizado = service.update(id, participante);
		return ResponseEntity.ok(new ParticipanteResponse(participanteAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
