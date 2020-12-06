package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Regional;
import me.henrique.syscredential.domain.repository.RegionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoRegionalService {

	@Autowired
	private RegionalRepository repository;

	public List<Regional> getAll() {
		return repository.findAll();
	}

	public Regional getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	public Regional save(Regional regional) {
		Optional<Regional> obj = repository.findByCod(regional.getCod());

		if (obj.isPresent()) {
			throw new DomainException("Regional já cadastrada");
		}

		return repository.save(regional);
	}

	public Regional update(Integer id, Regional regional) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		regional.setId(id);

		return repository.save(regional);
	}

	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
