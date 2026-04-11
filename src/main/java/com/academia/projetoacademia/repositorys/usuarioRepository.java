package com.academia.projetoacademia.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;


import com.academia.projetoacademia.Model.usuarioModel;

public interface usuarioRepository extends JpaRepository<usuarioModel, Long>{
}