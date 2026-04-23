package com.academia.projetoacademia.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private LocalDate DataAula;

    @Column(name = "horario_inicio")
    private LocalTime Horario;

    @Column
    private Long duracao;

    @ManyToOne
    @JoinColumn(name = "id_instrutor")
    private instrutorModel instrutorModel;

    @ManyToMany
    @JoinTable(
        name = "aula_equipamentos",
        joinColumns = @JoinColumn(name = "id_aula"),
        inverseJoinColumns = @JoinColumn(name = "id_equipamentos")
    )
    private List<equipamentosModel> equipamentosModels;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private categoriasModel categoriasModel;

    public LocalTime getHorario(){return Horario;}
}
