package me.henrique.syscredential.domain.services;

import me.henrique.syscredential.domain.model.Usuario;
import me.henrique.syscredential.domain.repository.UsuarioRepository;
import me.henrique.syscredential.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repo.findByLogin(login);

        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException(login);
        }

        return new UserSS(usuario.get().getId(), usuario.get().getLogin(), usuario.get().getSenha(), usuario.get().getPerfis());
    }
}
