package com.academia.projetoacademia.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class AlterarAulaDto {
    private LocalTime horaInicio;

    private LocalDate data;

    private Long duracao;

    private Long idAula;
}
