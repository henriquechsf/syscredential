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

import me.henrique.syscredential.api.dto.UsuarioInput;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.services.GestaoUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private GestaoUsuarioService service;

	@GetMapping
	public List<Usuario> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.listarPorId(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Usuario> adicionar(@Valid @RequestBody UsuarioInput dto) {
		Usuario usuario = new Usuario(dto);
		return ResponseEntity.ok(service.salvar(usuario));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody UsuarioInput dto) {
		Usuario usuario = new Usuario(dto);
		return ResponseEntity.ok(service.atualizar(id, usuario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}
