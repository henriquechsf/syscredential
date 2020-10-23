package me.henrique.syscredential.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import me.henrique.syscredential.api.dto.RegionalInput;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.services.CadastroRegional;

@RestController
@RequestMapping("/regionais")
public class RegionalController {

	@Autowired
	private CadastroRegional cadastroRegional;

	@GetMapping
	public List<Regional> listar() {
		return cadastroRegional.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Regional> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(cadastroRegional.listarPorId(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Regional> adicionar(@Valid @RequestBody RegionalInput dto) {
		Regional regional = new Regional(dto);
		return ResponseEntity.ok(cadastroRegional.salvar(regional));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Regional> atualizar(@PathVariable Integer id, @RequestBody RegionalInput dto) {
		Regional regional = new Regional(dto);
		return ResponseEntity.ok(cadastroRegional.atualizar(id, regional));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		cadastroRegional.remover(id);
		return ResponseEntity.noContent().build();
	}
}
