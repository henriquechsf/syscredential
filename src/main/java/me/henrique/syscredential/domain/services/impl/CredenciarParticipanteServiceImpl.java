package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.CredenciamentoRepository;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import me.henrique.syscredential.domain.services.CredenciarParticipanteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CredenciarParticipanteServiceImpl implements CredenciarParticipanteService {

    private EventoRepository eventoRepository;
    private ParticipanteRepository participanteRepository;
    private CredenciamentoRepository credenciamentoRepository;

    public CredenciarParticipanteServiceImpl(EventoRepository eventoRepository, ParticipanteRepository participanteRepository, CredenciamentoRepository credenciamentoRepository) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
        this.credenciamentoRepository = credenciamentoRepository;
    }

    @Override
    public Credenciamento credenciarParticipante(Integer idEvento, String credencial) {

        Optional<Evento> evento = eventoRepository.findById(idEvento);
        if (!evento.isPresent()) {
            throw new EntityNotFoundException("ID Evento inexistente");
        }

        Optional<Participante> participante = participanteRepository.findByCpf(credencial);
        if (!participante.isPresent()) {
            throw new EntityNotFoundException("Credencial não cadastrada.");
        }
        if (participante.isPresent() && !participante.get().getAtivo()) {
            throw new DomainException("Participante inativo.");
        }

        Optional<Credenciamento> credenciamentoParticipante = credenciamentoRepository.findParticipanteCredenciado(evento.get(), participante.get());
        if(credenciamentoParticipante.isPresent()) {
            throw new DomainException("Participante já credenciado.");
        }

        Credenciamento credenciamento = new Credenciamento();
        credenciamento.setParticipante(participante.get());
        credenciamento.setEvento(evento.get());
        credenciamento.setInstante(LocalDateTime.now());

        return credenciamentoRepository.save(credenciamento);
    }

    @Override
    public List<Credenciamento> listarParticipantesCredenciados(Integer idEvento) {
        Optional<Evento> evento = eventoRepository.findById(idEvento);

        if(!evento.isPresent()) {
            throw new EntityNotFoundException("ID Evento inexistente");
        }

        List<Credenciamento> credenciamentos = credenciamentoRepository.findAllParticipantesCredenciados(evento.get());

        return credenciamentos;
    }
}
