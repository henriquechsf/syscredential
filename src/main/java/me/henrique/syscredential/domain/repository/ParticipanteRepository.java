package me.henrique.syscredential.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.henrique.syscredential.domain.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    Optional<Participante> findByEmail(String email);
    Optional<Participante> findByCpf(String cpf);
    List<Participante> findByNome(String nome);
}
