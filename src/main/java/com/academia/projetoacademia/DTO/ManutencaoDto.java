package com.academia.projetoacademia.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ManutencaoDto {
    private String nomeEquipamento;

    private String motivo;

    private LocalDate dataSaida;

    private LocalDate dataRetorno;

    public String getNomeEquipamento(){return nomeEquipamento;}

    public String getMotivo(){return motivo;}

    public LocalDate getDataSaida(){return dataSaida;}

    public LocalDate getDataRetorno(){return dataRetorno;}

    public void setNomeEquipamento(String nomeEquipamento){this.nomeEquipamento = nomeEquipamento;}

    public void setMotivo(String motivo){this.motivo = motivo;}

    public void setDataSaida(LocalDate dataSaida){this.dataSaida = dataSaida;}

    public void setDataRetorno(LocalDate dataRetorno){this.dataRetorno = dataRetorno;}
}
