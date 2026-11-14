package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.PlanoModel;

public interface PlanosRepository extends JpaRepository<PlanoModel, Long>{
    Optional<PlanoModel> findByNome(String nome);
}
