package com.academia.projetoacademia.services;

import org.springframework.stereotype.Service;

import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.responseDTO;
import com.academia.projetoacademia.Model.categoriasModel;
import com.academia.projetoacademia.Model.equipamentosModel;
import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.categoriasRepositorys;
import com.academia.projetoacademia.repositorys.equipamentosRepositorys;
import com.academia.projetoacademia.repositorys.instrutorRepository;

@Service
public class aulasServices {
    private equipamentosRepositorys equip;

    private instrutorRepository instrutorRepository;

    private categoriasRepositorys categoriasRepository;


    public responseDTO criarAula(AulasDTO aulasDTO){
        responseDTO resposta = new responseDTO();
        equipamentosModel dadosEquipamento = equip.findByNome(aulasDTO.getEquipamento()).orElse(null);
        if (dadosEquipamento == null) {
            resposta.setSucesso(false);
            resposta.setMensagem("erro ao achar equipamento");
        }

        if ("manutencao".equalsIgnoreCase(dadosEquipamento.getStatus()) || "descartado".equalsIgnoreCase(dadosEquipamento.getStatus())) {
            resposta.setSucesso(false);
            resposta.setMensagem("nao e possivel usar um equipamento invalido");
            return resposta;
        }

        instrutorModel instrutorAula = instrutorRepository.findByName(aulasDTO.getInstrutor()).orElse(null);
        if(instrutorAula == null){
            resposta.setMensagem("instrutor nao encontrado");
            resposta.setSucesso(false);
            return resposta;
        }

        categoriasModel categoriasModel = categoriasRepository.findByNome(aulasDTO.getCategoria()).orElse(null);
        if(categoriasModel == null){
            resposta.setSucesso(false);
            resposta.setMensagem("categoria nao encontrada");
            return resposta;
        }

        resposta.setMensagem("aula criada com sucesso");
        resposta.setSucesso(true);
        return resposta;
    }

}
