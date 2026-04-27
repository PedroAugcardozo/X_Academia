package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.instrutorModel;

public interface instrutorRepository extends JpaRepository<instrutorModel, Long>{
    Optional<instrutorModel> findByNome(String nome);
}
