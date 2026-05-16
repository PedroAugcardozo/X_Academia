package com.academia.projetoacademia.Controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.DTO.InstrutorDto;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.repositorys.InstrutorRepository;
import com.academia.projetoacademia.services.InstrutorService;

@RequestMapping("/instrutor")
@RestController
public class Instrutor {

    @Autowired
    private InstrutorRepository repository;

    InstrutorService instrutorService;
    
    @PostMapping("/criar")
    public ResponseEntity criarInstrutor(@RequestBody InstrutorDto instrutor){
        try{
            ResponseDTO resposta = instrutorService.criarInstrutor(instrutor, repository);
            if(!resposta.getSucesso()){
                return ResponseEntity.badRequest().body(resposta.getMensagem());
            }
            return ResponseEntity.ok(resposta);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping("/buscarUm")
    public ResponseEntity buscarUm(@RequestParam Long id){
        return ResponseEntity.status(200).body(repository.findById(id));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity buscarTodos(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @DeleteMapping("/deletar")
    public ResponseEntity deletar(@RequestParam String nome){
        try{
            InstrutorModel instrutor = repository.findByNome(nome).orElse(null);
            if(instrutor == null){
                return ResponseEntity.badRequest().body("Instrutor nao encontrado");
            }

            repository.delete(instrutor);
            return ResponseEntity.ok("instrutor deletado com sucesso");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

}
