package com.academia.projetoacademia.DTO;

import java.time.LocalDate;

public class AulasDTO {
    private String nome;
    private LocalDate dataAula;
    private Long duracao;
    private String nomeinstrutor;
    private String nomeCategoria;
    private String nomeEquipamentos;

    public String getNome(){return nome;}

    public LocalDate getData(){return dataAula;}

    public Long getDuracao(){return duracao;}

    public String getInstrutor(){return nomeinstrutor;}

    public String getCategoria(){return nomeCategoria;}

    public String getEquipamento(){return nomeEquipamentos;}

    public void setNome(String nome){this.nome = nome;}

    public void setdataAula(LocalDate dataAula){this.dataAula = dataAula;}

    public void setDuracao(Long duracao){this.duracao = duracao;}

    public void setInstrutor(String nomeInstrutor){this.nomeinstrutor = nomeInstrutor;}

    public void setCategoria(String nomeCategoria){this.nomeCategoria = nomeCategoria;}

    public void setEquiamentos(String nomeEquipamentos){this.nomeEquipamentos = nomeEquipamentos;}
}
