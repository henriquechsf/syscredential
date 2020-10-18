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

import me.henrique.syscredential.api.dto.ParticipanteInput;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.services.CadastroParticipante;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	@Autowired
	private CadastroParticipante cadastroParticipante;

	@GetMapping
	public List<Participante> listar() {
		return cadastroParticipante.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participante> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(cadastroParticipante.listarPorId(id));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Participante> listarPorCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(cadastroParticipante.listarPorCpf(cpf));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Participante> adicionar(@Valid @RequestBody ParticipanteInput dto) {
		Participante participante = new Participante(dto);
		return ResponseEntity.ok(cadastroParticipante.salvar(participante));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Participante> atualizar(@PathVariable Integer id, @RequestBody ParticipanteInput dto) {
		Participante participante = new Participante(dto);
		return ResponseEntity.ok(cadastroParticipante.atualizar(id, participante));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		cadastroParticipante.remover(id);
		return ResponseEntity.noContent().build();
	}
}
