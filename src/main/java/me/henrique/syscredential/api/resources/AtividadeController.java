package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.AtividadeRequest;
import me.henrique.syscredential.api.dto.response.AtividadeResponse;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.services.GestaoAtividadeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	private ModelMapper mapper;
	private GestaoAtividadeService service;

	public AtividadeController(ModelMapper mapper, GestaoAtividadeService service) {
		this.mapper = mapper;
		this.service = service;
	}

	@GetMapping
	public List<AtividadeResponse> listar() {
		List<Atividade> atividades = service.getAll();
		List<AtividadeResponse> response = atividades
				.stream().map(atividade -> mapper.map(atividade, AtividadeResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<AtividadeResponse> listarPorId(@PathVariable Integer id) {
		Atividade atividade = service.getById(id);
		AtividadeResponse response = mapper.map(atividade, AtividadeResponse.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AtividadeResponse> adicionar(@Valid @RequestBody AtividadeRequest request) {
		Atividade atividade = mapper.map(request, Atividade.class);
		Atividade atividadeSalva = service.save(atividade);
		AtividadeResponse response = mapper.map(atividadeSalva, AtividadeResponse.class);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AtividadeResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody AtividadeRequest request) {
		Atividade atividade = mapper.map(request, Atividade.class);
		Atividade atividadeAtualizada = service.update(id, atividade);
		AtividadeResponse response = mapper.map(atividadeAtualizada, AtividadeResponse.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
