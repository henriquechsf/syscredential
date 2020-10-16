package me.henrique.syscredential.api.controller;

import java.util.List;
import java.util.Optional;

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

import me.henrique.syscredential.api.dto.AtividadeFormDto;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.repository.AtividadeRepository;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	@Autowired
	private AtividadeRepository atividadeRepository;

	@GetMapping
	public List<Atividade> listar() {
		return atividadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> listarPorId(@PathVariable Integer id) {
		Optional<Atividade> atividade = atividadeRepository.findById(id);

		if (atividade.isPresent()) {
			return ResponseEntity.ok(atividade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Atividade adicionar(@Valid @RequestBody AtividadeFormDto dto) {
		Atividade atividade = new Atividade(dto);
		return atividadeRepository.save(atividade);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@PathVariable Integer id, @RequestBody Atividade atividade) {
		if (!atividadeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}

		atividade.setId(id);
		atividade = atividadeRepository.save(atividade);

		return ResponseEntity.ok(atividade);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if (!atividadeRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
	
		atividadeRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}
