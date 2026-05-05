package com.academia.projetoacademia.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "historico_manutencao")
public class ManutencaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "data_retorno")
    private LocalDate dataRetorno;

    @Column
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_equipamento")
    private EquipamentosModel equipamentos;

    @ManyToOne
    @JoinColumn(name = "id_admin_responsavel")
    private UsuarioModel adminResponsavel;
}
