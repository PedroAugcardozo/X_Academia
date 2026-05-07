package com.academia.projetoacademia.Controll;

import com.academia.projetoacademia.services.AlunosService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.DTO.UsuariosDto;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/usuarios")
public class Usuario {

    private final AlunosService alunosService;

    private final UsuarioRepository repository;

    public Usuario(UsuarioRepository repository, AlunosService alunosService) {
        this.repository = repository;
        this.alunosService = alunosService;
    }

    @PostMapping("/criar/aluno")
    public ResponseEntity CriarUsuario(@RequestBody UsuariosDto usuario){
        try{
            ResponseDTO resposta = alunosService.criarAluno(usuario);
            if(!resposta.getSucesso()){
                return ResponseEntity.badRequest().body(resposta);
            }
            return ResponseEntity.ok().body(resposta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("erro ao criar um aluno");
        }
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity BuscarUsuarios(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/buscarum")
    public ResponseEntity BuscarUm(@RequestParam Long id){
        return ResponseEntity.ok("Usuario: " + repository.findById(id));
    }

    @PostMapping("/atualizar-plano")
    public ResponseEntity atualizarPlano(@AuthenticationPrincipal usuarioAutenticado usuarioLogado,@RequestParam Long idPlano){
        try{
            ResponseDTO resposta = alunosService.atualizarPlano(usuarioLogado, idPlano);
            if(!resposta.getSucesso()){
                return ResponseEntity.badRequest().body(resposta);
            }
            return ResponseEntity.ok().body(resposta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("erro ao atualizar plano");
        }
    }
}
/*
{
  "email": "admin@academia.com",
  "senha": "admin123"

  "email": "aluno1@academia.com",
  "senha": "aluno123"

  "email": "instrutor1@academia.com",
  "senha": "instrutor123"
}
*/
