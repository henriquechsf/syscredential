package me.henrique.syscredential.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.RecursoNaoEncontradoException;
import me.henrique.syscredential.domain.model.Atividade;
import me.henrique.syscredential.domain.repository.AtividadeRepository;

@Service
public class GestaoAtividadeService {

	@Autowired
	private AtividadeRepository atividadeRepository;

	public List<Atividade> listar() {
		return atividadeRepository.findAll();
	}

	public Atividade listarPorId(Integer id) {
		return atividadeRepository.findById(id).orElseThrow(() -> new DomainException("ID não encontrado"));
	}

	public Atividade salvar(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public Atividade atualizar(Integer id, Atividade atividade) {
		if (!atividadeRepository.existsById(id)) {
			throw new DomainException("ID não encontrado");
		}
		atividade.setId(id);

		return atividadeRepository.save(atividade);
	}

	public void remover(Integer id) {
		if (!atividadeRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("ID não encontrado");
		}
		atividadeRepository.deleteById(id);
	}

}
