package com.academia.projetoacademia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.aulasModel;

public interface aulasRepository extends JpaRepository<aulasModel, Long>{
    List<aulasModel> findByInstrutorModelId(Long id);
}
