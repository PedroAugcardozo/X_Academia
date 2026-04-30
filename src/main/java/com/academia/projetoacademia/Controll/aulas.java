package com.academia.projetoacademia.Controll;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.DTO.AulasDTO;
import com.academia.projetoacademia.DTO.ResponseDTO;

import com.academia.projetoacademia.repositorys.AulasRepository;
import com.academia.projetoacademia.services.AulasServices;



@RestController
@RequestMapping("/aulas")
public class Aulas {
    private final AulasServices aulasServices;

    private final Instrutor instrutor;

    private final Equipamentos equipamentos;

    private AulasRepository repository;

    private ResponseDTO response;

    Aulas(Equipamentos equipamentos, Instrutor instrutor, AulasServices aulasServices) {
        this.equipamentos = equipamentos;
        this.instrutor = instrutor;
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
    
}
