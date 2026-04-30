package com.academia.projetoacademia.configuracaoSeguranca;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

@Service
public class usuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    public usuarioDetailsService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuario = repository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new usuarioAutenticado(usuario);
    }
}
