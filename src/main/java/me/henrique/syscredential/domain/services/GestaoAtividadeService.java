package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoAtividadeService {

	@Autowired
	public AtividadeRepository repository;

	public List<Atividade> getAll() {
		return repository.findAll();
	}

	public Atividade getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Transactional
	public Atividade save(Atividade atividade) {
		return repository.save(atividade);
	}

	@Transactional
	public Atividade update(Integer id, Atividade atividade) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		atividade.setId(id);

		return repository.save(atividade);
	}

	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
