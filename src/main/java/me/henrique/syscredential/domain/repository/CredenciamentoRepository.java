package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredenciamentoRepository extends JpaRepository<Credenciamento, Long> {
    public Credenciamento findByParticipante(Participante participante);
    public Credenciamento findByEvento(Evento evento);
}
