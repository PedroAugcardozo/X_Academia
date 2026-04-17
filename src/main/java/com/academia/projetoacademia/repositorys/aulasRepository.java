package com.academia.projetoacademia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.aulasModel;
import com.academia.projetoacademia.Model.usuarioModel;

public interface aulasRepository extends JpaRepository<aulasModel, Long>{
    
}
