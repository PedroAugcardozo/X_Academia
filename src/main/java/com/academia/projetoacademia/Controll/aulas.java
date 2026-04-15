package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.aulasModel;
import com.academia.projetoacademia.repositorys.aulasRepository;

@RestController
@RequestMapping("/aulas")
public class aulas {
    
    private aulasRepository repository;

    @PostMapping("/criar")
    public ResponseEntity criarAulas(@RequestBody aulasModel novaAula){
        return ResponseEntity.status(201).body(repository.save(novaAula));
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
