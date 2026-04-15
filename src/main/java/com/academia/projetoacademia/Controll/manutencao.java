package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.manutencaoMode;
import com.academia.projetoacademia.repositorys.manutencaoRepository;

@RestController
@RequestMapping("/manutencao")
public class manutencao {

    private manutencaoRepository repository;
    
    @PostMapping("/criar")
    public ResponseEntity criarManutencao(@RequestBody manutencaoMode manutencao){
        return ResponseEntity.status(201).body(repository.save(manutencao));
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
