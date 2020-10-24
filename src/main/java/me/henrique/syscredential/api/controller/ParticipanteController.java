package me.henrique.syscredential.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.henrique.syscredential.api.dto.ParticipanteInput;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.services.GestaoParticipanteService;

@CrossOrigin
@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	@Autowired
	private GestaoParticipanteService service;

	@GetMapping
	public List<Participante> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participante> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(service.listarPorId(id));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Participante> listarPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(service.listarPorCpf(cpf));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Participante> adicionar(@Valid @RequestBody ParticipanteInput dto) {
		Participante participante = new Participante(dto);
		return ResponseEntity.ok(service.salvar(participante));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Participante> atualizar(@PathVariable Integer id, @RequestBody ParticipanteInput dto) {
		Participante participante = new Participante(dto);
		return ResponseEntity.ok(service.atualizar(id, participante));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		service.remover(id);
		return ResponseEntity.noContent().build();
	}
}
