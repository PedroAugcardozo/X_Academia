package com.academia.projetoacademia.Controll;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.DTO.ManutencaoDto;
import com.academia.projetoacademia.Model.EquipamentosModel;
import com.academia.projetoacademia.Model.ManutencaoModel;
import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.EquipamentosRepositorys;
import com.academia.projetoacademia.repositorys.ManutencaoRepository;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

@RestController
@RequestMapping("/manutencao")
public class Manutencao {

    private ManutencaoRepository repository;

    private UsuarioRepository usuarioRepository;

    private EquipamentosRepositorys equipamentosRepositorys;
    
    @PostMapping("/criar")
    public ResponseEntity criarManutencao(@RequestBody ManutencaoDto manutencaoDto, @AuthenticationPrincipal usuarioAutenticado autenticado){
        try{
            ManutencaoModel manutencaoModel = new ManutencaoModel();
            Optional<UsuarioModel> usuarioOpt = usuarioRepository.findById(autenticado.getId());
            if(usuarioOpt.isPresent()){
                manutencaoModel.setAdminResponsavel(usuarioOpt.get());
            }else{
                return ResponseEntity.badRequest().body("usuario nao cadastrado");
            }
            Optional<EquipamentosModel> equipamentoOpt = equipamentosRepositorys.findByNome(manutencaoDto.getNomeEquipamento());
            if(equipamentoOpt.isPresent()){
                manutencaoModel.setEquipamentos(equipamentoOpt.get());
            } else{
                return ResponseEntity.badRequest().body("equipamento nao encontrado");
            }
            manutencaoModel.setDataSaida(manutencaoDto.getDataSaida());
            manutencaoModel.setDataRetorno(manutencaoDto.getDataRetorno());
            manutencaoModel.setMotivo(manutencaoDto.getMotivo());
            return ResponseEntity.status(201).body(repository.save(manutencaoModel));
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/buscarTodas")
    public ResponseEntity buscarTodas(){
        return ResponseEntity.status(201).body(repository.findAll());
    }
    @GetMapping("/buscarUma")
    public ResponseEntity buscarUma(@RequestParam Long id){
        return ResponseEntity.status(201).body(repository.findById(id));
    }
}
