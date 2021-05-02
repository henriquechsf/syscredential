package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Credenciamento;
import me.henrique.syscredential.domain.model.Evento;
import me.henrique.syscredential.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredenciamentoRepository extends JpaRepository<Credenciamento, Long> {
    public Credenciamento findByParticipante(Participante participante);
    public Credenciamento findByEvento(Evento evento);

    @Query("SELECT credenciamento FROM Credenciamento credenciamento " +
            " JOIN credenciamento.evento ON credenciamento.evento = :evento ORDER BY credenciamento.instante DESC")
    public List<Credenciamento> findAllParticipantesCredenciados(@Param("evento") Evento evento);

}
