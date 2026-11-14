package com.academia.projetoacademia.Model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios_aulas")
@Data
@NoArgsConstructor
public class InscricaoAulaModel {
    @EmbeddedId
    private InscricaoAulaId id = new InscricaoAulaId();

    @ManyToOne
    @MapsId("idUsuario") // Nome do campo dentro da classe InscricaoAulaId
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    @ManyToOne
    @MapsId("idAula") // Nome do campo dentro da classe InscricaoAulaId
    @JoinColumn(name = "id_aula")
    private AulasModel aula;

    @Column(nullable = false)
    private boolean presenca = false;
}
