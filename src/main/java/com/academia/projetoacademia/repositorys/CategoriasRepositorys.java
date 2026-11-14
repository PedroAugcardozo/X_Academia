package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.CategoriasModel;

public interface CategoriasRepositorys extends JpaRepository<CategoriasModel, Long>{
    Optional<CategoriasModel> findByNome(String name);
}
