package com.academia.projetoacademia.repositorys;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.academia.projetoacademia.Model.usuarioModel;

public interface usuarioRepository extends JpaRepository<usuarioModel, Long>{
    Optional<usuarioModel> findByEmail(String email);
    Optional<usuarioModel> findByNome(String nome);
}