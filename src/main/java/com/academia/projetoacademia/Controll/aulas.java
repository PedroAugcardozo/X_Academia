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
import com.academia.projetoacademia.DTO.responseDTO;
import com.academia.projetoacademia.Model.aulasModel;
import com.academia.projetoacademia.Model.categoriasModel;
import com.academia.projetoacademia.Model.equipamentosModel;
import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.aulasRepository;
import com.academia.projetoacademia.repositorys.equipamentosRepositorys;
import com.academia.projetoacademia.repositorys.instrutorRepository;
import com.academia.projetoacademia.services.aulasServices;
import com.academia.projetoacademia.repositorys.categoriasRepositorys;


@RestController
@RequestMapping("/aulas")
public class aulas {
    private final aulasServices aulasServices;

    private final instrutor instrutor;

    private final equipamentos equipamentos;

    private aulasRepository repository;

    private responseDTO response;

    aulas(equipamentos equipamentos, instrutor instrutor, aulasServices aulasServices) {
        this.equipamentos = equipamentos;
        this.instrutor = instrutor;
        this.aulasServices = aulasServices;
    }
    @PostMapping("/criar")
    public ResponseEntity criarAulas(@RequestBody AulasDTO aulasDTO){
        response = aulasServices.criarAula(aulasDTO);
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
