package com.academia.projetoacademia.DTO;

import java.time.LocalDate;

public class InstrutorDto{
    private String nome;
    private String email;
    private String cpf;
    private String senha;
    private String sexo;
    private LocalDate dataDeNascimento;
    private String cref;

    public String getNome(){return nome;}

    public String getEmail(){return email;}

    public String getCpf(){return cpf;}

    public String getSenha(){return senha;}

    public String getSexo(){return sexo;}

    public LocalDate getDataDeNascimento(){return dataDeNascimento;}

    public String getCref(){return cref;}

    public void setNome(String nome){this.nome = nome;}

    public void setEmail(String email){this.email = email;}

    public void setCpf(String cpf){this.cpf = cpf;}

    public void setDataDeNascimento(LocalDate dataDeNascimento){this.dataDeNascimento = dataDeNascimento;}

    public void setCref(String cref){this.cref = cref;}

    public void setSenha(String senha){this.senha = senha;}

    public void setSexo(String sexo){this.sexo = sexo;}
}
