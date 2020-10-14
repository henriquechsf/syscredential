package me.henrique.syscredential.api.controller;

import java.util.List;
import java.util.Optional;

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

import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

	@Autowired
	private ParticipanteRepository participanteRepository;
	
	@GetMapping
	public List<Participante> listar() {
		return participanteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Participante> listarPorId(Integer id) {
		Optional<Participante> participante = participanteRepository.findById(id);

		if (participante.isPresent()) {
			return ResponseEntity.ok(participante.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Participante adicionar(@RequestBody Participante participante) {
		return participanteRepository.save(participante);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Participante> atualizar(@PathVariable Integer id, @RequestBody Participante participante) {
		if (!participanteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		participante.setId(id);
		participante = participanteRepository.save(participante);

		return ResponseEntity.ok(participante);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!participanteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	
		participanteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
