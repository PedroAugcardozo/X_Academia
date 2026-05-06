package com.academia.projetoacademia.DTO;

import java.time.LocalDate;

public class UsuariosDto {
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String sexo;
    private LocalDate dataDeNascimento;
    private Long planoId;

    public String getNome(){return nome;}

    public String getEmail(){return email;}

    public String getCpf(){return cpf;}

    public String getSenha(){return senha;}

    public String getSexo(){return sexo;}

    public LocalDate getDataDeNascimento(){return dataDeNascimento;}

    public Long getPlanoId(){return planoId;}

    public void setNome(String nome){this.nome = nome;}

    public void setEmail(String email){this.email = email;}

    public void setCpf(String cpf){this.cpf = cpf;}

    public void setDataDeNascimento(LocalDate dataDeNascimento){this.dataDeNascimento = dataDeNascimento;}

    public void setPlanoId(Long planoId){this.planoId = planoId;}

    public void setSenha(String senha){this.senha = senha;}

    public void setSexo(String sexo){this.sexo = sexo;}
}
