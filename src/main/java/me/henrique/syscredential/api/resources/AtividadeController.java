package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.AtividadeRequest;
import me.henrique.syscredential.api.dto.response.AtividadeResponse;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.services.GestaoAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	@Autowired
	private GestaoAtividadeService service;

	@GetMapping
	public List<AtividadeResponse> listar() {
		List<Atividade> atividades = service.getAll();
		List<AtividadeResponse> atividadesResponse = new ArrayList<>();
		atividades.forEach(atividade -> atividadesResponse.add(new AtividadeResponse(atividade)));
		return atividadesResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<AtividadeResponse> listarPorId(@PathVariable Integer id) {
		Atividade atividade = service.getById(id);
		return ResponseEntity.ok(new AtividadeResponse(atividade));
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AtividadeResponse> adicionar(@Valid @RequestBody AtividadeRequest dto) {
		Atividade atividade = new Atividade(dto);
		Atividade atividadeSalva = service.save(atividade);
		return ResponseEntity.ok(new AtividadeResponse(atividadeSalva));
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<AtividadeResponse> atualizar(@PathVariable Integer id, @RequestBody AtividadeRequest dto) {
		Atividade atividade = new Atividade(dto);
		Atividade atividadeAtualizada = service.update(id, atividade);
		return ResponseEntity.ok(new AtividadeResponse(atividadeAtualizada));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
