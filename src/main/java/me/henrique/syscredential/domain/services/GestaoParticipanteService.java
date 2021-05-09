package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoParticipanteService {

	@Autowired
	private ParticipanteRepository participanteRepository;

	public List<Participante> getAll() {
		return participanteRepository.findAll();
	}

	public Participante getById(Integer id) {
		return participanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	public Participante getByCpf(String cpf) {
		return participanteRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Participante não cadastrado"));
	}

	public Participante save(Participante participante) {
		Optional<Participante> obj = participanteRepository.findByCpf(participante.getCpf());

		if (obj.isPresent()) {
			throw new DomainException("CPF já cadastrado");
		}

		return participanteRepository.save(participante);
	}

	public Participante update(Integer id, Participante participante) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participante.setId(id);

		return participanteRepository.save(participante);
	}

	public void delete(Integer id) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participanteRepository.deleteById(id);
	}

}
