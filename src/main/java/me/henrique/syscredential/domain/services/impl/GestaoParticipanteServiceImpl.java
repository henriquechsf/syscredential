package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import me.henrique.syscredential.domain.services.GestaoParticipanteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoParticipanteServiceImpl implements GestaoParticipanteService {

	private ParticipanteRepository participanteRepository;

	public GestaoParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
		this.participanteRepository = participanteRepository;
	}

	@Override
	public List<Participante> getAll() {
		return participanteRepository.findAll();
	}

	@Override
	public Participante getById(Integer id) {
		return participanteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Override
	public Participante getByCpf(String cpf) {
		return participanteRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Participante não cadastrado"));
	}

	@Override
	@Transactional
	public Participante save(Participante participante) {
		if (participanteRepository.existsByCpf(participante.getCpf())) {
			throw new DomainException("CPF já cadastrado");
		}

		return participanteRepository.save(participante);
	}

	@Override
	@Transactional
	public Participante update(Integer id, Participante participante) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participante.setId(id);

		return participanteRepository.save(participante);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!participanteRepository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		participanteRepository.deleteById(id);
	}

}
