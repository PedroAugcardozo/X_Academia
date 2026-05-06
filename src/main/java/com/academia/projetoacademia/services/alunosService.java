package com.academia.projetoacademia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.DTO.UsuariosDto;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.Model.PlanoModel;
import com.academia.projetoacademia.Model.UsuarioModel;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;
import com.academia.projetoacademia.repositorys.InstrutorRepository;
import com.academia.projetoacademia.repositorys.PlanosRepository;
import com.academia.projetoacademia.repositorys.UsuarioRepository;

@Service
public class AlunosService {

    private final PasswordEncoder passwordEncoder;

    private ResponseDTO resposta = new ResponseDTO();

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private InstrutorRepository instRepository;

    @Autowired
    private PlanosRepository planosRepository;

    private UsuarioModel usuarioModel;

    AlunosService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseDTO atualizarPlano(usuarioAutenticado usuarioLogado, Long planoId){
        try{
            Optional<UsuarioModel> usuarioOpt = repository.findById(usuarioLogado.getId());
            if (usuarioOpt.isEmpty()) {
                resposta.setMensagem("usuario nao encontrado");
                resposta.setSucesso(false);
                return resposta;
            }
            Long id = usuarioLogado.getId();
            Optional<InstrutorModel> instrutorOpt = instRepository.findById(id);
            if (instrutorOpt.isPresent()) {
                resposta.setMensagem("Instrutor nao pode alterar um plano");
                resposta.setSucesso(false);
                return resposta;
            }
            Optional<PlanoModel> planoOpt = planosRepository.findById(planoId);
            if (planoOpt.isEmpty()) {
                resposta.setMensagem("Plano nao encontrado");
                resposta.setSucesso(false);
                return resposta;
            }
            UsuarioModel usuario = usuarioOpt.get();
            PlanoModel plano = planoOpt.get();
            usuario.setPlano(plano);
            usuario.setPlano_expira_em(LocalDateTime.now().plusDays(30));
            repository.save(usuario);

            resposta.setMensagem("plano atualizado com sucesso");
            resposta.setSucesso(true);
            return resposta;
        } catch(Exception e){
            resposta.setMensagem("ERRO: " + e);
            resposta.setSucesso(false);
            return resposta;
        }
    }

    public ResponseDTO criarAluno(UsuariosDto novoUsuario){
        try{

            PlanoModel plano = planosRepository.findById(novoUsuario.getPlanoId()).orElse(null);

            LocalDateTime data = LocalDateTime.now();

            usuarioModel = new UsuarioModel();

            usuarioModel.setCpf(novoUsuario.getCpf());
            usuarioModel.setEmail(novoUsuario.getEmail());
            usuarioModel.setNome(novoUsuario.getNome());
            usuarioModel.setPlano(plano);
            usuarioModel.setSexo(novoUsuario.getSexo());
            usuarioModel.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
            usuarioModel.setData_nasc(novoUsuario.getDataDeNascimento());
            usuarioModel.setNivel_acesso("aluno");
            usuarioModel.setPlano_expira_em(data.plusDays(30));

            UsuarioModel usuarioCriado = repository.save(usuarioModel);

            resposta.setId(usuarioCriado.getId());
            resposta.setMensagem("Aluno criado com sucesso");
            resposta.setSucesso(true);

            return resposta;
        }catch (Exception e){
            resposta.setMensagem("erro de servidor");
            resposta.setSucesso(false);
            return resposta;
        }   
    }
}
