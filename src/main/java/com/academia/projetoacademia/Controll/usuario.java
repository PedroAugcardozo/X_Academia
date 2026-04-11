package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.usuarioModel;
import com.academia.projetoacademia.repositorys.usuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/usuarios")
public class usuario {

    private final usuarioRepository repository;

    private usuarioModel usuarioAux;

    public usuario(usuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/criar")
    public ResponseEntity CriarUsuario(@RequestBody usuarioModel usuario){
        usuarioAux = repository.save(usuario);
        if(usuarioAux != null){
            return ResponseEntity.ok("usuario criado com sucesso");
        }
        return ResponseEntity.badRequest().body("erro ao criar usuario");
    }

    @GetMapping("/buscartodos")
    public ResponseEntity BuscarUsuarios(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/buscarum")
    public ResponseEntity BuscarUm(@RequestParam Long id){
        return ResponseEntity.ok("Usuario: " + repository.findById(id));
    }
}
