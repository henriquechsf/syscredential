package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoParticipanteService {

	private ParticipanteRepository participanteRepository;

	public GestaoParticipanteService(ParticipanteRepository participanteRepository) {
		this.participanteRepository = participanteRepository;
	}

	public List<Participante> getAll() {
		return participanteRepository.findAll();
	}

	public Participante getById(Integer id) {
		return participanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	public Participante getByCpf(String cpf) {
		return participanteRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Participante não cadastrado"));
	}

	@Transactional
	public Participante save(Participante participante) {
		if (participanteRepository.existsByCpf(participante.getCpf())) {
			throw new DomainException("CPF já cadastrado");
		}

		return participanteRepository.save(participante);
	}

	@Transactional
	public Participante update(Integer id, Participante participante) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participante.setId(id);

		return participanteRepository.save(participante);
	}

	@Transactional
	public void delete(Integer id) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participanteRepository.deleteById(id);
	}

}
