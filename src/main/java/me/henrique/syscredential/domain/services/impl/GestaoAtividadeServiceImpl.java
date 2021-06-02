package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.repository.AtividadeRepository;
import me.henrique.syscredential.domain.services.GestaoAtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoAtividadeServiceImpl implements GestaoAtividadeService {

	@Autowired
	public AtividadeRepository repository;

	@Override
	public List<Atividade> getAll() {
		return repository.findAll();
	}

	@Override
	public Atividade getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Override
	@Transactional
	public Atividade save(Atividade atividade) {
		return repository.save(atividade);
	}

	@Override
	@Transactional
	public Atividade update(Integer id, Atividade atividade) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		atividade.setId(id);

		return repository.save(atividade);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
