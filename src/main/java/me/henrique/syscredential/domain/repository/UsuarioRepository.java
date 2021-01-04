package me.henrique.syscredential.domain.repository;

import me.henrique.syscredential.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public Optional<Usuario> findByEmail(String email);
}
