package me.henrique.syscredential.domain.services.impl;

import me.henrique.syscredential.domain.exception.DomainException;
import me.henrique.syscredential.domain.exception.EntityNotFoundException;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.repository.UsuarioRepository;
import me.henrique.syscredential.domain.services.GestaoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GestaoUsuarioServiceImpl implements GestaoUsuarioService {

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private UsuarioRepository repository;

	@Override
	public List<Usuario> getAll() {
		return repository.findAll();
	}

	@Override
	public Usuario getById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
	}

	@Override
	public Usuario getByLogin(String login) {
		return repository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("Login não encontrado"));
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		Optional<Usuario> obj = repository.findByLogin(usuario.getLogin());

		if (obj.isPresent()) {
			throw new DomainException("Login já cadastrado");
		}

		usuario.setSenha(bcrypt.encode(usuario.getSenha()));

		return repository.save(usuario);
	}

	@Override
	@Transactional
	public Usuario update(Integer id, Usuario usuario) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		usuario.setId(id);

		return repository.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntityNotFoundException("ID não encontrado");
		}
		repository.deleteById(id);
	}
}
