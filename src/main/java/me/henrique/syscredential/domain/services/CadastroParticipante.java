package me.henrique.syscredential.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.NegocioException;
import me.henrique.syscredential.domain.exception.RecursoNaoEncontradoException;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;

@Service
public class CadastroParticipante {

	@Autowired
	private ParticipanteRepository participanteRepository;

	public List<Participante> listar() {
		return participanteRepository.findAll();
	}

	public Participante listarPorId(Integer id) {
		return participanteRepository.findById(id).orElseThrow(() -> new NegocioException("ID não encontrado"));
	}

	public Participante listarPorCpf(String cpf) {
		return participanteRepository.findByCpf(cpf).orElseThrow(() -> new NegocioException("CPF não encontrado"));
	}

	public Participante salvar(Participante participante) {
		Optional<Participante> obj = participanteRepository.findByCpf(participante.getCpf());

		if (obj.isPresent()) {
			throw new NegocioException("Participante já cadastrado");
		}

		return participanteRepository.save(participante);
	}

	public Participante atualizar(Integer id, Participante participante) {
		if (!participanteRepository.existsById(id)) {
			throw new NegocioException("ID não encontrado");
		}
		participante.setId(id);

		return participanteRepository.save(participante);
	}

	public void remover(Integer id) {
		if (!participanteRepository.existsById(id)) {
			throw new RecursoNaoEncontradoException("ID não encontrado");
		}
		participanteRepository.deleteById(id);
	}

}
