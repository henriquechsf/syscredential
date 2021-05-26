package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.ParticipanteRequest;
import me.henrique.syscredential.api.dto.response.ParticipanteResponse;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.services.GestaoParticipanteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	private ModelMapper mapper;
	private GestaoParticipanteService service;

	public ParticipanteController(ModelMapper mapper, GestaoParticipanteService service) {
		this.mapper = mapper;
		this.service = service;
	}

	@GetMapping
	public List<ParticipanteResponse> listar() {
		List<Participante> participantes = service.getAll();
		List<ParticipanteResponse> participantesResponse = participantes
				.stream().map(participante -> mapper.map(participante, ParticipanteResponse.class))
				.collect(Collectors.toList());

		return participantesResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticipanteResponse> listarPorId(@PathVariable Integer id) {
		Participante participante = service.getById(id);
		ParticipanteResponse response = mapper.map(participante, ParticipanteResponse.class);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ParticipanteResponse> listarPorCpf(@PathVariable String cpf) {
		Participante participante = service.getByCpf(cpf);
		ParticipanteResponse response = mapper.map(participante, ParticipanteResponse.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParticipanteResponse> adicionar(@Valid @RequestBody ParticipanteRequest request) {
		Participante participante = mapper.map(request, Participante.class);
		Participante participanteSalvo = service.save(participante);
		ParticipanteResponse response = mapper.map(participanteSalvo, ParticipanteResponse.class);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticipanteResponse> atualizar(@PathVariable Integer id, @RequestBody ParticipanteRequest request) {
		Participante participante = mapper.map(request, Participante.class);
		Participante participanteAtualizado = service.update(id, participante);
		ParticipanteResponse response = mapper.map(participanteAtualizado, ParticipanteResponse.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
