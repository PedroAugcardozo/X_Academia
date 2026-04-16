package com.academia.projetoacademia.configuracaoSeguranca;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.academia.projetoacademia.Model.usuarioModel;
import com.academia.projetoacademia.repositorys.usuarioRepository;

@Service
public class usuarioDetailsService implements UserDetailsService{
    private usuarioRepository repository;

    public void usuarioDetailsService(usuarioRepository repository) {
        this.repository = repository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        usuarioModel usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new usuarioAutenticado(usuario);
    }
}
