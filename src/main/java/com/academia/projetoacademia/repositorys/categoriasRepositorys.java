package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.categoriasModel;

public interface categoriasRepositorys extends JpaRepository<categoriasModel, Long>{
    Optional<categoriasModel> findByNome(String name);
}
