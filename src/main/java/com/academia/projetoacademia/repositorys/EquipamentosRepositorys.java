package com.academia.projetoacademia.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.EquipamentosModel;

public interface EquipamentosRepositorys extends JpaRepository<EquipamentosModel, Long>{
    Optional<EquipamentosModel> findByNome(String nome);
}
