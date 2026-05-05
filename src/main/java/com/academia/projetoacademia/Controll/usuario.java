package com.academia.projetoacademia.Controll;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.Model.PlanoModel;
import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.InstrutorRepository;
import com.academia.projetoacademia.repositorys.PlanosRepository;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/usuarios")
public class Usuario {

    private final UsuarioRepository repository;

    private final InstrutorRepository instRepository;

    private final PlanosRepository planosRepository;

    public Usuario(UsuarioRepository repository, InstrutorRepository instrutorRepository, PlanosRepository planosRepository) {
        this.repository = repository;
        this.instRepository = instrutorRepository;
        this.planosRepository = planosRepository;
    }
    

    @PostMapping("/criar")
    public ResponseEntity CriarUsuario(@RequestBody UsuarioModel usuario){
        return ResponseEntity.status(201).body(repository.save(usuario));
    }

    @GetMapping("/buscartodos")
    public ResponseEntity BuscarUsuarios(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/buscarum")
    public ResponseEntity BuscarUm(@RequestParam Long id){
        return ResponseEntity.ok("Usuario: " + repository.findById(id));
    }

    @PostMapping("/atualizar-plano")
    public ResponseEntity atualizarPlano(@AuthenticationPrincipal usuarioAutenticado usuarioLogado,@RequestParam Long idPlano) {
        try {
            Optional<UsuarioModel> usuarioOpt = repository.findById(usuarioLogado.getId());
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("usuario nao encontrado");
            }
            Long id = usuarioLogado.getId();
            Optional<InstrutorModel> instrutorOpt = instRepository.findById(id);
            if (instrutorOpt.isPresent()) {
                return ResponseEntity.badRequest().body("instrutor nao pode atualizar o proprio plano");
            }
            Optional<PlanoModel> planoOpt = planosRepository.findById(idPlano);
            if (planoOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("plano nao encontrado");
            }
            UsuarioModel usuario = usuarioOpt.get();
            PlanoModel plano = planoOpt.get();
            usuario.setPlano(plano);
            usuario.setPlano_expira_em(LocalDateTime.now().plusDays(30));
            repository.save(usuario);
            return ResponseEntity.ok("plano atualizado com sucesso");
        } catch (Exception e) {
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