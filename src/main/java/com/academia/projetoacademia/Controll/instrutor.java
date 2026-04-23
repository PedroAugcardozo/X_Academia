package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.instrutorRepository;

@RequestMapping("/instrutor")
@RestController
public class instrutor {

    private instrutorRepository repository;
    
    @PostMapping("/criar")
    public ResponseEntity criarInstrutor(@RequestBody instrutorModel instrutor){
        return ResponseEntity.status(201).body(repository.save(instrutor));
    }
}
