package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Usuario;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GestaoUsuarioService {
    List<Usuario> getAll();

    Usuario getById(Integer id);

    Usuario getByLogin(String login);

    @Transactional
    Usuario save(Usuario usuario);

    @Transactional
    Usuario update(Integer id, Usuario usuario);

    @Transactional
    void delete(Integer id);
}
