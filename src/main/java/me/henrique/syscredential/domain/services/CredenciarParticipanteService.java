package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.api.dto.request.CredenciamentoRequest;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.CredenciamentoRepository;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CredenciarParticipanteService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private CredenciamentoRepository credenciamentoRepository;

    public Credenciamento credenciarParticipante(Integer idEvento, CredenciamentoRequest request) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);

        if (!evento.isPresent()) {
            throw new EntityNotFoundException("ID Evento inexistente");
        }

        Optional<Participante> participante = participanteRepository.findByCpf(request.getCpf());

        if (!participante.isPresent()) {
            throw new EntityNotFoundException("CPF do Participante não cadastrado");
        }

        Credenciamento credenciamento = new Credenciamento(LocalDateTime.now(), evento.get(), participante.get());

        return credenciamentoRepository.save(credenciamento);
    }

    public List<Credenciamento> listarParticipantesCredenciados(Integer idEvento) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);

        if(!evento.isPresent()) {
            throw new EntityNotFoundException("ID Evento inexistente");
        }

        List<Credenciamento> credenciamentos = credenciamentoRepository.findAllParticipantesCredenciados(evento.get());

        return credenciamentos;
    }
}
