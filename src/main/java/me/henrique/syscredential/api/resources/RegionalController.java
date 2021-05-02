package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.RegionalRequest;
import me.henrique.syscredential.api.dto.response.RegionalResponse;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.services.GestaoRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/regionais")
public class RegionalController {

	@Autowired
	private GestaoRegionalService service;

	@GetMapping
	public List<RegionalResponse> listar() {
		List<Regional> regionais = service.getAll();
		List<RegionalResponse> regionaisResponse = new ArrayList<>();
		regionais.forEach(regional -> regionaisResponse.add(new RegionalResponse(regional)));
		return regionaisResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegionalResponse> listarPorId(@PathVariable Integer id) {
		Regional regional = service.getById(id);
		return ResponseEntity.ok(new RegionalResponse(regional));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegionalResponse> adicionar(@Valid @RequestBody RegionalRequest dto) {
		Regional regional = new Regional(dto);
		Regional regionalSalva = service.save(regional);
		return ResponseEntity.ok(new RegionalResponse(regionalSalva));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RegionalResponse> atualizar(@PathVariable Integer id, @RequestBody RegionalRequest dto) {
		Regional regional = new Regional(dto);
		Regional regionalAtualizada = service.update(id, regional);
		return ResponseEntity.ok(new RegionalResponse(regionalAtualizada));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
