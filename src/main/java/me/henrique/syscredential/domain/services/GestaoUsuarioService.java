package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> getAll() {
		return repository.findAll();
	}

	public Usuario getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	public Usuario save(Usuario usuario) {
		Optional<Usuario> obj = repository.findByLogin(usuario.getLogin());

		if (obj.isPresent()) {
			throw new DomainException("Login já cadastrado");
		}

		return repository.save(usuario);
	}

	public Usuario update(Integer id, Usuario usuario) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		usuario.setId(id);

		return repository.save(usuario);
	}

	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}
