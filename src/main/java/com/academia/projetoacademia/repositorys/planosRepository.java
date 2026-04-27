package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.planoModel;

public interface planosRepository extends JpaRepository<planoModel, Long>{
    Optional<planoModel> findByNome(String nome);
}
