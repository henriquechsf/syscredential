package me.henrique.syscredential.api.resources;

import me.henrique.syscredential.api.dto.request.UsuarioRequest;
import me.henrique.syscredential.api.dto.response.UsuarioResponse;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.services.GestaoUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private GestaoUsuarioService service;

	public UsuarioController(GestaoUsuarioService service) {
		this.service = service;
	}

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

	@GetMapping("/login/{login}")
	public ResponseEntity<UsuarioResponse> listarPorLogin(@PathVariable String login) {
		Usuario usuario = service.getByLogin(login);
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
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequest dto) {
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
