package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Integer> {
}
