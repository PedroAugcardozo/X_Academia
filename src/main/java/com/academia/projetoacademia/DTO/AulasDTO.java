package com.academia.projetoacademia.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AulasDTO {
    private String nome;
    private LocalDate dataAula;
    private Long duracao;
    private String nomeinstrutor;
    private String nomeCategoria;
    private List<String> nomeEquipamentos;
    private LocalTime horario;

    public String getNome(){return nome;}

    public LocalDate getData(){return dataAula;}

    public Long getDuracao(){return duracao;}

    public String getInstrutor(){return nomeinstrutor;}

    public String getCategoria(){return nomeCategoria;}

    public List<String> getEquipamento(){return nomeEquipamentos;}

    public LocalTime getHorario(){return horario;}

    public void setNome(String nome){this.nome = nome;}

    public void setdataAula(LocalDate dataAula){this.dataAula = dataAula;}

    public void setDuracao(Long duracao){this.duracao = duracao;}

    public void setInstrutor(String nomeInstrutor){this.nomeinstrutor = nomeInstrutor;}

    public void setCategoria(String nomeCategoria){this.nomeCategoria = nomeCategoria;}

    public void setEquiamentos(List<String> nomeEquipamentos){this.nomeEquipamentos = nomeEquipamentos;}

    public void setHorario(LocalTime horario){this.horario = horario;}
}
