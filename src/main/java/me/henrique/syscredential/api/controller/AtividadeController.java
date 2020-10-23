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

import me.henrique.syscredential.api.dto.AtividadeInput;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.services.CadastroAtividade;

@RestController
@RequestMapping("/atividades")
public class AtividadeController {

	@Autowired
	private CadastroAtividade cadastroAtividade;

	@GetMapping
	public List<Atividade> listar() {
		return cadastroAtividade.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> listarPorId(@PathVariable Integer id) {
		return ResponseEntity.ok(cadastroAtividade.listarPorId(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Atividade> adicionar(@Valid @RequestBody AtividadeInput dto) {
		Atividade atividade = new Atividade(dto);
		return ResponseEntity.ok(cadastroAtividade.salvar(atividade));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Atividade> atualizar(@PathVariable Integer id, @RequestBody AtividadeInput dto) {
		Atividade atividade = new Atividade(dto);
		return ResponseEntity.ok(cadastroAtividade.atualizar(id, atividade));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		cadastroAtividade.remover(id);
		return ResponseEntity.noContent().build();
	}
}
