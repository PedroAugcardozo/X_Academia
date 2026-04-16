package com.academia.projetoacademia.DTO;

public class RespostaDto {
     private String token;
    private String tipo;
    private Long id;
    private String nome;
    private String nivelAcesso;

    public RespostaDto(String token, String tipo, Long id, String nome, String nivelAcesso) {
        this.token = token;
        this.tipo = tipo;
        this.id = id;
        this.nome = nome;
        this.nivelAcesso = nivelAcesso;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }
}
