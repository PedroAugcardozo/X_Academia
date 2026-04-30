package com.academia.projetoacademia.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscricaoAulaId implements Serializable{
    private Long idUsuario;
    private Long idAula;
}
