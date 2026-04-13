package com.academia.projetoacademia.Model;

import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "aulas")
@NoArgsConstructor
public class aulasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column(name = "data_aula")
    private Date DataAula;

    @Column(name = "horario_inicio")
    private LocalTime Horario;

    @Column
    private LocalTime duracao;

    @ManyToOne
    @JoinColumn(name = "id_instrutor")
    private instrutorModel instrutorModel;
}
