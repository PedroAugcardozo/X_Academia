package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.InstrutorModel;

public interface InstrutorRepository extends JpaRepository<InstrutorModel, Long>{
    Optional<InstrutorModel> findByNome(String nome);
}
