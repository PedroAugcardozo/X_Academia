package com.academia.projetoacademia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.inscricaoAulaId;
import com.academia.projetoacademia.Model.inscricaoAulaModel;

public interface inscricaoAulaRepositorys extends JpaRepository<inscricaoAulaModel, inscricaoAulaId>{
    List<inscricaoAulaModel> findByAulaId(Long idAula);
    
    List<inscricaoAulaModel> findByUsuarioId(Long idUsuario);
}
