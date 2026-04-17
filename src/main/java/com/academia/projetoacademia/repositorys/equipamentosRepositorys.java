package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.equipamentosModel;

public interface equipamentosRepositorys extends JpaRepository<equipamentosModel, Long>{
    Optional<equipamentosModel> findByNome(String nome);
}
