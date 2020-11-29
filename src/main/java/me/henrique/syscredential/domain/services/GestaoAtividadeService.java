package me.henrique.syscredential.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.repository.AtividadeRepository;

@Service
public class GestaoAtividadeService {

	@Autowired
	public AtividadeRepository repository;

	public List<Atividade> listar() {
		return repository.findAll();
	}

	public Atividade listarPorId(Integer id) {
		return repository.findById(id).orElseThrow(() -> new DomainException("ID não encontrado"));
	}

	public Atividade salvar(Atividade atividade) {
		return repository.save(atividade);
	}

	public Atividade atualizar(Integer id, Atividade atividade) {
		if (!repository.existsById(id)) {
			throw new DomainException("ID não encontrado");
		}
		atividade.setId(id);

		return repository.save(atividade);
	}

	public void remover(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
