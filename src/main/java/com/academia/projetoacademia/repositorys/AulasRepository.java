package com.academia.projetoacademia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.AulasModel;

public interface AulasRepository extends JpaRepository<AulasModel, Long>{
    List<AulasModel> findByInstrutorModelId(Long id);
}
