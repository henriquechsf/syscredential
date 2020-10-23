package me.henrique.syscredential.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.NegocioException;
import me.henrique.syscredential.domain.exception.RecursoNaoEncontradoException;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.RegionalRepository;

@Service
public class GestaoRegionalService {

	@Autowired
	private RegionalRepository regionalRepository;

	public List<Regional> listar() {
		return regionalRepository.findAll();
	}

	public Regional listarPorId(Integer id) {
		return regionalRepository.findById(id).orElseThrow(() -> new NegocioException("ID não encontrado"));
	}

	public Regional salvar(Regional regional) {
		Optional<Regional> obj = regionalRepository.findById(regional.getId());

		if (obj.isPresent()) {
			throw new NegocioException("Regional já cadastrada");
		}

		return regionalRepository.save(regional);
	}

	public Regional atualizar(Integer id, Regional regional) {
		if (!regionalRepository.existsById(id)) {
			throw new NegocioException("ID não encontrado");
		}
		regional.setId(id);

		return regionalRepository.save(regional);
	}

	public void remover(Integer id) {
		if (!regionalRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("ID não encontrado");
		}
		regionalRepository.deleteById(id);
	}

}
