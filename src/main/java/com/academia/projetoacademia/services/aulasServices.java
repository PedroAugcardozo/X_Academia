package com.academia.projetoacademia.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.Model.AulasModel;
import com.academia.projetoacademia.Model.CategoriasModel;
import com.academia.projetoacademia.Model.EquipamentosModel;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.repositorys.AulasRepository;
import com.academia.projetoacademia.repositorys.CategoriasRepositorys;
import com.academia.projetoacademia.repositorys.EquipamentosRepositorys;
import com.academia.projetoacademia.repositorys.InstrutorRepository;

@Service
public class AulasServices {
    @Autowired
    private EquipamentosRepositorys equip;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private CategoriasRepositorys categoriasRepository;

    private AulasModel aulasModel;

    List<EquipamentosModel> equipamentosDaAula = new ArrayList<>();
    public ResponseDTO criarAula(AulasDTO aulasDTO, AulasRepository repository){
        ResponseDTO resposta = new ResponseDTO();
        try{
            for (String nomeEquipamento : aulasDTO.getEquipamento()) {
                EquipamentosModel dadosEquipamento = equip.findByNome(nomeEquipamento).orElse(null);

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

            InstrutorModel instrutorAula = instrutorRepository.findByNome(aulasDTO.getInstrutor()).orElse(null);
            if(instrutorAula == null){
                resposta.setMensagem("instrutor nao encontrado");
                resposta.setSucesso(false);
                return resposta;
            }

            CategoriasModel categoriasModel = categoriasRepository.findByNome(aulasDTO.getCategoria()).orElse(null);
            if(categoriasModel == null){
                resposta.setSucesso(false);
                resposta.setMensagem("categoria nao encontrada");
                return resposta;
            }

            for(AulasModel aulas: repository.findByInstrutorModelId(instrutorAula.getId())){
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
