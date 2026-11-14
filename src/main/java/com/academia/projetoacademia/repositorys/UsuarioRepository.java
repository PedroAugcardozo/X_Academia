package com.academia.projetoacademia.repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.academia.projetoacademia.Model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{
    Optional<UsuarioModel> findByEmail(String email);
    Optional<UsuarioModel> findByNome(String nome);
}