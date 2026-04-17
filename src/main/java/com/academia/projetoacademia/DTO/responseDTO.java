package com.academia.projetoacademia.DTO;

public class responseDTO {
    private Boolean sucesso;
    private String mensagem;

    public Boolean getSucesso(){return sucesso;}

    public String getMensagem(){return mensagem;}

    public void setSucesso(Boolean sucesso){this.sucesso = sucesso;}

    public void setMensagem(String mensagem){this.mensagem = mensagem;}
}
