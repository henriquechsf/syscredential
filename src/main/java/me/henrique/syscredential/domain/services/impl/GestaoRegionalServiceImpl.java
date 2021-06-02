package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.RegionalRepository;
import me.henrique.syscredential.domain.services.GestaoRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoRegionalServiceImpl implements GestaoRegionalService {

	@Autowired
	private RegionalRepository repository;

	@Override
	public List<Regional> getAll() {
		return repository.findAll();
	}

	@Override
	public Regional getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Override
	@Transactional
	public Regional save(Regional regional) {
		Optional<Regional> obj = repository.findByCod(regional.getCod());

		if (obj.isPresent()) {
			throw new DomainException("Regional já cadastrada");
		}

		return repository.save(regional);
	}

	@Override
	@Transactional
	public Regional update(Integer id, Regional regional) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		regional.setId(id);

		return repository.save(regional);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}

		try {
			repository.deleteById(id);
		} catch (RuntimeException e) {
			throw new DomainException("Não foi possível excluir");
		}
	}

}
