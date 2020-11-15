package me.henrique.syscredential.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.henrique.syscredential.domain.exception.NegocioException;
import me.henrique.syscredential.domain.exception.EntidadeNaoEncontradaException;
import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.repository.UsuarioRepository;

@Service
public class GestaoUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> listar() {
		return repository.findAll();
	}

	public Usuario listarPorId(Integer id) {
		return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("ID não encontrado"));
	}

	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> obj = repository.findByLogin(usuario.getLogin());

		if (obj.isPresent()) {
			throw new NegocioException("Login já cadastrado");
		}

		return repository.save(usuario);
	}

	public Usuario atualizar(Integer id, Usuario usuario) {
		if (!repository.existsById(id)) {
			throw new EntidadeNaoEncontradaException("ID não encontrado");
		}
		usuario.setId(id);

		return repository.save(usuario);
	}

	public void remover(Integer id) {
		if (!repository.existsById(id)) {
			throw new EntidadeNaoEncontradaException("ID não encontrado");
		}
		repository.deleteById(id);
	}

}