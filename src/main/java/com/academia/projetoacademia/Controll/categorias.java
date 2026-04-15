package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.categoriasModel;
import com.academia.projetoacademia.repositorys.categoraiasRepositorys;

@RestController
@RequestMapping("/categorias")
public class categorias {

    private categoraiasRepositorys repositorys;
    
    @PostMapping("/criar")
    public ResponseEntity criarCategoria(@RequestBody categoriasModel categoriasModel){
        return ResponseEntity.status(201).body(repositorys.save(categoriasModel));
    }

    @GetMapping("/buscarTodas")
    public ResponseEntity buscarTodas(){
        return ResponseEntity.status(201).body(repositorys.findAll());
    }

    @GetMapping("/buscarUma")
    public ResponseEntity buscarUma(@RequestParam Long id){
        return ResponseEntity.status(201).body(repositorys.findById(id));
    }
}
