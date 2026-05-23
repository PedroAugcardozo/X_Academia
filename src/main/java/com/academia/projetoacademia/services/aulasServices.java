package com.academia.projetoacademia.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.projetoacademia.DTO.AlterarAulaDto;
import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.Model.AulasModel;
import com.academia.projetoacademia.Model.CategoriasModel;
import com.academia.projetoacademia.Model.EquipamentosModel;
import com.academia.projetoacademia.Model.InscricaoAulaModel;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.AulasRepository;
import com.academia.projetoacademia.repositorys.CategoriasRepositorys;
import com.academia.projetoacademia.repositorys.EquipamentosRepositorys;
import com.academia.projetoacademia.repositorys.InscricaoAulaRepositorys;
import com.academia.projetoacademia.repositorys.InstrutorRepository;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

@Service
public class AulasServices {
    @Autowired
    private EquipamentosRepositorys equip;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private CategoriasRepositorys categoriasRepository;

    @Autowired
    private AulasRepository aulasRepository;

    @Autowired
    private InscricaoAulaRepositorys inscricaoAulaRepositorys;

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

    public ResponseDTO alterarHorario(AlterarAulaDto novaAula, usuarioAutenticado usuario){
        ResponseDTO resposta = new ResponseDTO();
        try{

            AulasModel aula = aulasRepository.findById(novaAula.getIdAula()).orElse(null);
            if(aula == null){
                resposta.setMensagem("aula nao encontrada");
                resposta.setSucesso(false);
                return resposta;
            }

            InstrutorModel instrutorDaAula = instrutorRepository.findById(usuario.getId()).orElse(null);
            if(instrutorDaAula == null){
                resposta.setMensagem("instrutor nao encontrado");
                resposta.setSucesso(false);
                return resposta;
            }

            for(AulasModel aulas: aulasRepository.findByInstrutorModelId(instrutorDaAula.getId())){
                if(aulas.getDataAula().equals(novaAula.getData()) ){
                    LocalTime fimAulaInstrutor = aulas.getHorario().plusMinutes(aulas.getDuracao());
                    LocalTime fimNovaAula = novaAula.getHoraInicio().plusMinutes(novaAula.getDuracao());
                    if(novaAula.getHoraInicio().isBefore(fimAulaInstrutor) && fimNovaAula.isAfter(aulas.getHorario())){
                        resposta.setMensagem("instrutor ja tem uma aula nesse horario");
                        resposta.setSucesso(false);
                        return resposta;
                    }
                }
            }
            aula.setDataAula(novaAula.getData());
            aula.setDuracao(novaAula.getDuracao());
            aula.setHorario(novaAula.getHoraInicio());

            aulasRepository.save(aula);
            
            return resposta;
        }catch(Exception e){
            return resposta;
        }
    }

    public ResponseDTO cadastrarPresenca(usuarioAutenticado usuario, UsuarioRepository repository, Long idAula){
        ResponseDTO resposta = new ResponseDTO();
        try{   
            UsuarioModel usuarioOpt = repository.findById(usuario.getId()).orElse(null);
            if(usuarioOpt == null){
                resposta.setMensagem("usuario nao encontrado");
                resposta.setSucesso(false);
                return resposta;
            }

            AulasModel aula = aulasRepository.findById(idAula).orElse(null);
            if(aula == null){
                resposta.setMensagem("aula nao encontrada");
                resposta.setSucesso(false);
                return resposta;
            }

            if(!inscricaoAulaRepositorys.existsByAulaIdAndUsuarioId(idAula, usuario.getId())){
                resposta.setMensagem("aluno nao cadastrado na aula");
                resposta.setSucesso(false);
                return resposta;
            }

            InscricaoAulaModel inscricaoAulaModel = inscricaoAulaRepositorys.findByAulaIdAndUsuarioId(idAula, usuario.getId()).orElse(null);

            if(inscricaoAulaModel == null){
                resposta.setMensagem("erro ao cadastrar presenca");
                resposta.setSucesso(false);
                return resposta;
            }
            inscricaoAulaModel.setPresenca(true);
            inscricaoAulaRepositorys.save(inscricaoAulaModel);

            resposta.setMensagem("presenca cadastrada com sucesso");
            resposta.setSucesso(true);
            resposta.setId(usuario.getId());
            return resposta;
        }catch (Exception e){
            resposta.setMensagem("Erro na requisição" + e);
            resposta.setSucesso(false);
            return resposta;
        }
    }
}
