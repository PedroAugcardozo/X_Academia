package com.academia.projetoacademia.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuariosDto {
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String sexo;
    private LocalDate dataDeNascimento;
    private Long planoId;
}
