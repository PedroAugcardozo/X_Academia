package com.academia.projetoacademia.Controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.CategoriasModel;
import com.academia.projetoacademia.repositorys.CategoriasRepositorys;

@RestController
@RequestMapping("/categorias")
public class Categorias {

    @Autowired
    private CategoriasRepositorys repositorys;
    
    @PostMapping("/criar")
    public ResponseEntity criarCategoria(@RequestBody CategoriasModel categoriasModel){
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
