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
import com.academia.projetoacademia.Model.aulasModel;
import com.academia.projetoacademia.Model.equipamentosModel;
import com.academia.projetoacademia.Model.instrutorModel;
import com.academia.projetoacademia.repositorys.aulasRepository;
import com.academia.projetoacademia.repositorys.equipamentosRepositorys;
import com.academia.projetoacademia.repositorys.instrutorRepository;

@RestController
@RequestMapping("/aulas")
public class aulas {
    private final instrutor instrutor;

    private final equipamentos equipamentos;

    private aulasRepository repository;

    private aulasModel aulasModel;

    private equipamentosRepositorys equip;

    private instrutorRepository instrutorRepository;

    aulas(equipamentos equipamentos, instrutor instrutor) {
        this.equipamentos = equipamentos;
        this.instrutor = instrutor;
    }

    @PostMapping("/criar")
    public ResponseEntity criarAulas(@RequestBody AulasDTO aulasDTO){
        equipamentosModel dadosEquipamento = equip.findByNome(aulasDTO.getEquipamento()).orElse(null);
        if (dadosEquipamento == null) {
            return ResponseEntity.badRequest().body("Equipamento não encontrado");
        }
        if ("manutencao".equalsIgnoreCase(dadosEquipamento.getStatus()) || "descartado".equalsIgnoreCase(dadosEquipamento.getStatus())) {
            return ResponseEntity.badRequest().body("Não é possível cadastrar aula com equipamento em manutenção");
        }

        instrutorModel instrutorAula = instrutorRepository.findByName(aulasDTO.getInstrutor()).orElse(null);
        if(instrutorAula == null){
            return ResponseEntity.badRequest().body("Instrutor nao encontrado");
        }
        

        return ResponseEntity.ok("Aula criada com sucesso");
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
