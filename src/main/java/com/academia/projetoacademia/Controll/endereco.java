package com.academia.projetoacademia.Controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.EnderecoModel;
import com.academia.projetoacademia.repositorys.EnderecoRepository;

@RestController
@RequestMapping("/endereco")
public class Endereco {
    
    @Autowired
    private EnderecoRepository repository;

    @PostMapping("/criar")
    public ResponseEntity criarEndereco(@RequestBody EnderecoModel enderecoModel){
        return ResponseEntity.status(201).body(repository.save(enderecoModel));
    }

    @GetMapping("/buscarUm")
    public ResponseEntity buscarEndereco(@RequestParam Long id){
        return ResponseEntity.status(201).body(repository.findById(id));
    }
}
