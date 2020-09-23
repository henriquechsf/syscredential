package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {

    Participante findByEmail(String email);
    Participante findByCpf(String cpf);
    List<Participante> findByNome(String nome);
}
