package com.academia.projetoacademia.Controll;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.repositorys.planosRepository;

@RestController
@RequestMapping(name = "/planos")
public class plano {

    private planosRepository repository;
    
    @PostMapping("/criar")
    public ResponseEntity CriarPlano(@RequestBody com.academia.projetoacademia.Model.planoModel plano){
        try{
            return ResponseEntity.status(201).body(repository.save(plano));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodos(){
        return ResponseEntity.status(201).body(repository.findAll());
    }
}
