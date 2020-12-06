package me.henrique.syscredential.controller;

import me.henrique.syscredential.controller.request.UsuarioRequest;
import me.henrique.syscredential.controller.response.UsuarioResponse;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.services.GestaoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private GestaoUsuarioService service;

	@GetMapping
	public List<UsuarioResponse> listar() {
		List<Usuario> usuarios = service.getAll();
		List<UsuarioResponse> usuariosResponse = new ArrayList<>();
		usuarios.forEach(usuario -> usuariosResponse.add(new UsuarioResponse(usuario)));
		return usuariosResponse;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioResponse> listarPorId(@PathVariable Integer id) {
		Usuario usuario = service.getById(id);
		return ResponseEntity.ok(new UsuarioResponse(usuario));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioResponse> adicionar(@Valid @RequestBody UsuarioRequest dto) {
		Usuario usuario = new Usuario(dto);
		Usuario usuarioSalvo = service.save(usuario);
		return ResponseEntity.ok(new UsuarioResponse(usuarioSalvo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id, @RequestBody UsuarioRequest dto) {
		Usuario usuario = new Usuario(dto);
		Usuario usuarioAtualizado = service.update(id, usuario);
		return ResponseEntity.ok(new UsuarioResponse(usuarioAtualizado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
