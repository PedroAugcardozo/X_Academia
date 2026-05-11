package com.academia.projetoacademia.Controll;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.Model.AulasModel;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.AulasRepository;
import com.academia.projetoacademia.repositorys.UsuarioRepository;
import com.academia.projetoacademia.services.AulasServices;



@RestController
@RequestMapping("/aulas")
public class Aulas {
    private final AulasServices aulasServices;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private AulasRepository repository;

    private ResponseDTO response;

    Aulas(Equipamentos equipamentos, Instrutor instrutor, AulasServices aulasServices) {
        this.aulasServices = aulasServices;
    }
    @PostMapping("/criar")
    public ResponseEntity criarAulas(@RequestBody AulasDTO aulasDTO){
        response = aulasServices.criarAula(aulasDTO, repository);
        if(response.getSucesso()){
            return ResponseEntity.ok(response.getMensagem());
        }
        return ResponseEntity.badRequest().body(response.getMensagem());
    }

    @GetMapping("/buscarTodas")
    public ResponseEntity buscarTodos(){
        return ResponseEntity.status(201).body(repository.findAll());
    }

    @GetMapping("/buscarUma")
    public ResponseEntity buscarUm(@RequestParam Long id){
        return ResponseEntity.status(201).body(repository.findById(id));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam Long idAula, @AuthenticationPrincipal usuarioAutenticado autenticado){
        try{
            Optional<UsuarioModel> usuarioModel = usuarioRepository.findById(autenticado.getId());
            if(usuarioModel.isEmpty()){
                return ResponseEntity.badRequest().body("usuario nao encontrado");
            }

            //fiz de um jeito diferente do de cima pra ficar legal
            AulasModel OptAulas = repository.findById(idAula).orElse(null);
            if(OptAulas == null){
                return ResponseEntity.badRequest().body("aula nao encontrada");
            }

            if("ADMIN".equals(autenticado.getNivelAcesso())){
                repository.delete(OptAulas);
                return ResponseEntity.ok().body("aula deletada por admin com sucesso");
            }

            InstrutorModel instrutorDaAula = OptAulas.getInstrutorModel();
            if(!Objects.equals(instrutorDaAula.getId(), autenticado.getId())){
                return ResponseEntity.badRequest().body("somente o instrutor que criou a aula pode deletar ela");
            }

            repository.delete(OptAulas);
            return ResponseEntity.ok("aula deletada com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
