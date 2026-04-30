package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.InstrutorRepository;

@RequestMapping("/instrutor")
@RestController
public class Instrutor {

    private InstrutorRepository repository;
    
    @PostMapping("/criar")
    public ResponseEntity criarInstrutor(@RequestBody instrutorModel instrutor){
        return ResponseEntity.status(201).body(repository.save(instrutor));
    }

    @GetMapping("/buscarUm")
    public ResponseEntity buscarUm(@RequestParam Long id){
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodos(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

}
