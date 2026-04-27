package com.academia.projetoacademia.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.responseDTO;
import com.academia.projetoacademia.Model.aulasModel;
import com.academia.projetoacademia.Model.categoriasModel;
import com.academia.projetoacademia.Model.equipamentosModel;
import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.aulasRepository;
import com.academia.projetoacademia.repositorys.categoriasRepositorys;
import com.academia.projetoacademia.repositorys.equipamentosRepositorys;
import com.academia.projetoacademia.repositorys.instrutorRepository;

@Service
public class aulasServices {
    private equipamentosRepositorys equip;

    private instrutorRepository instrutorRepository;

    private categoriasRepositorys categoriasRepository;

    private aulasModel aulasModel;

    List<equipamentosModel> equipamentosDaAula = new ArrayList<>();

    public responseDTO criarAula(AulasDTO aulasDTO, aulasRepository repository){
        responseDTO resposta = new responseDTO();
        try{
            for (String nomeEquipamento : aulasDTO.getEquipamento()) {
                equipamentosModel dadosEquipamento = equip.findByNome(nomeEquipamento).orElse(null);

                if (dadosEquipamento == null) {
                    resposta.setSucesso(false);
                    resposta.setMensagem("erro ao achar equipamento: " + nomeEquipamento);
                    return resposta;
                }

                if ("manutencao".equalsIgnoreCase(dadosEquipamento.getStatus()) ||
                    "descartado".equalsIgnoreCase(dadosEquipamento.getStatus())) {
                    resposta.setSucesso(false);
                    resposta.setMensagem("nao e possivel usar um equipamento invalido: " + nomeEquipamento);
                    return resposta;
                }

                equipamentosDaAula.add(dadosEquipamento);
            }

            instrutorModel instrutorAula = instrutorRepository.findByNome(aulasDTO.getInstrutor()).orElse(null);
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

            for(aulasModel aulas: repository.findByInstrutorModelId(instrutorAula.getId())){
                if(aulas.getDataAula().equals(aulasDTO.getData()) ){
                    LocalTime fimAulaInstrutor = aulas.getHorario().plusMinutes(aulas.getDuracao());
                    LocalTime fimNovaAula = aulasDTO.getHorario().plusMinutes(aulasDTO.getDuracao());
                    if(aulasDTO.getHorario().isBefore(fimAulaInstrutor) && fimNovaAula.isAfter(aulas.getHorario())){
                        resposta.setMensagem("instrutor ja tem uma aula nesse horario");
                        resposta.setSucesso(false);
                        return resposta;
                    }
                }
            }

            aulasModel.setDuracao(aulasDTO.getDuracao());
            aulasModel.setDataAula(aulasDTO.getData());
            aulasModel.setHorario(aulasDTO.getHorario());
            aulasModel.setNome(aulasDTO.getNome());
            aulasModel.setInstrutorModel(instrutorAula);
            aulasModel.setCategoriasModel(categoriasModel);
            aulasModel.setEquipamentosModels(equipamentosDaAula);

            
            repository.save(aulasModel);
            resposta.setMensagem("aula criada com sucesso");
            resposta.setSucesso(true);

        }catch(RuntimeException e){
            resposta.setMensagem(e.getMessage());
            resposta.setSucesso(false);
        }
        return resposta;
    }

}
