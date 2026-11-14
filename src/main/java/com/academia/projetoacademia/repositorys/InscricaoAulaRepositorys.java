package com.academia.projetoacademia.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.projetoacademia.Model.InscricaoAulaId;
import com.academia.projetoacademia.Model.InscricaoAulaModel;

public interface InscricaoAulaRepositorys extends JpaRepository<InscricaoAulaModel, InscricaoAulaId>{
    List<InscricaoAulaModel> findByAulaId(Long idAula);
    
    List<InscricaoAulaModel> findByUsuarioId(Long idUsuario);

    boolean existsByAulaIdAndUsuarioId(Long idAula, Long idUsuario);

    Optional<InscricaoAulaModel> findByAulaIdAndUsuarioId(Long idAula, Long idUsuario);
}
