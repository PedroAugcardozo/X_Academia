package com.academia.projetoacademia.DTO;

public class ResponseDTO {
    private Boolean sucesso;
    private String mensagem;
    private Long id;

    public ResponseDTO(){
        id = null;
    }

    public Boolean getSucesso(){return sucesso;}

    public String getMensagem(){return mensagem;}

    public Long getId(){return id;}

    public void setSucesso(Boolean sucesso){this.sucesso = sucesso;}

    public void setMensagem(String mensagem){this.mensagem = mensagem;}

    public void setId(Long id){this.id = id;}
}
