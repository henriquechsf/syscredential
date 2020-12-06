package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Regional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionalRepository extends JpaRepository<Regional, Integer> {
    Optional<Regional> findByCod(Integer cod);
}
