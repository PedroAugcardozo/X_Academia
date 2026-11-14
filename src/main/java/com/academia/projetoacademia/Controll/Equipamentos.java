package com.academia.projetoacademia.Controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.EquipamentosModel;
import com.academia.projetoacademia.repositorys.EquipamentosRepositorys;

@RestController
@RequestMapping("/equipamentos")
public class Equipamentos {
    
    @Autowired
    private EquipamentosRepositorys repositorys;

    @PostMapping("/criar")
    public ResponseEntity criarEquipamento(@RequestBody EquipamentosModel equipamento){
        return ResponseEntity.status(201).body(repositorys.save(equipamento));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodos(){
        return ResponseEntity.status(201).body(repositorys.findAll());
    }

    @GetMapping("/buscarUm")
    public ResponseEntity buscarUm(@RequestParam Long id){
        return ResponseEntity.status(201).body(repositorys.findById(id));
    }
}
