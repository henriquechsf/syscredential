package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import me.henrique.syscredential.domain.repository.CredenciamentoRepository;
import me.henrique.syscredential.domain.repository.EventoRepository;
import me.henrique.syscredential.domain.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CredenciarParticipanteService {

    private EventoRepository eventoRepository;
    private ParticipanteRepository participanteRepository;
    private CredenciamentoRepository credenciamentoRepository;

    public CredenciarParticipanteService(EventoRepository eventoRepository, ParticipanteRepository participanteRepository, CredenciamentoRepository credenciamentoRepository) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
        this.credenciamentoRepository = credenciamentoRepository;
    }

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

        Optional<Credenciamento> credenciamentoParticipante = credenciamentoRepository.findByParticipante(participante.get());
        if(credenciamentoParticipante.isPresent()) {
            throw new DomainException("Participante já credenciado.");
        }

        Credenciamento credenciamento = Credenciamento.builder()
                .instante(LocalDateTime.now())
                .evento(evento.get())
                .participante(participante.get())
                .build();

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
