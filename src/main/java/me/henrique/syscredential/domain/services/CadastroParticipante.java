package me.henrique.syscredential.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.NegocioException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;

@Service
public class CadastroParticipante {
	
	@Autowired
	private ParticipanteRepository participanteRepository;
	
	public Participante salvar(Participante participante) {
		Optional<Participante> obj = participanteRepository.findByCpf(participante.getCpf());
		
		if (obj.isPresent()) {
			throw new NegocioException("Participante já cadastrado");
		}
		
		return participanteRepository.save(participante);
	}
}
