package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.RegionalRequest;
import me.henrique.syscredential.api.dto.response.RegionalResponse;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.services.GestaoRegionalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regionais")
public class RegionalController {

	private ModelMapper mapper;
	private GestaoRegionalService service;

	public RegionalController(ModelMapper mapper, GestaoRegionalService service) {
		this.mapper = mapper;
		this.service = service;
	}

	@GetMapping
	public List<RegionalResponse> listar() {
		List<Regional> regionais = service.getAll();
		List<RegionalResponse> response = regionais
				.stream().map(regional -> mapper.map(regional, RegionalResponse.class))
				.collect(Collectors.toList());

		return response;
	}

	@GetMapping("/{id}")
	public ResponseEntity<RegionalResponse> listarPorId(@PathVariable Integer id) {
		Regional regional = service.getById(id);
		RegionalResponse response = mapper.map(regional, RegionalResponse.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RegionalResponse> adicionar(@Valid @RequestBody RegionalRequest request) {
		Regional regional = mapper.map(request, Regional.class);
		Regional regionalSalva = service.save(regional);
		RegionalResponse response = mapper.map(regionalSalva, RegionalResponse.class);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<RegionalResponse> atualizar(@PathVariable Integer id, @RequestBody RegionalRequest request) {
		Regional regional = mapper.map(request, Regional.class);
		Regional regionalAtualizada = service.update(id, regional);
		RegionalResponse response = mapper.map(regionalAtualizada, RegionalResponse.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
