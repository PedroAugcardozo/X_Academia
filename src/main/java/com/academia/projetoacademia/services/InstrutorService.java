package com.academia.projetoacademia.services;

import com.academia.projetoacademia.DTO.InstrutorDto;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.Model.InstrutorModel;
import com.academia.projetoacademia.repositorys.InstrutorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InstrutorService {

    private final PasswordEncoder passwordEncoder;

    InstrutorService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    
    ResponseDTO resposta;

    public ResponseDTO criarInstrutor(InstrutorDto novoInstrutor, InstrutorRepository repository){
        try{
            InstrutorModel instrutorModel = new InstrutorModel();
            
            instrutorModel.setNome(novoInstrutor.getNome());
            instrutorModel.setCpf(novoInstrutor.getCpf());
            instrutorModel.setCref(novoInstrutor.getCref());
            instrutorModel.setData_nasc(novoInstrutor.getDataDeNascimento());
            instrutorModel.setEmail(novoInstrutor.getEmail());
            instrutorModel.setSenha(passwordEncoder.encode(novoInstrutor.getSenha()));
            instrutorModel.setSexo(novoInstrutor.getSexo());
            instrutorModel.setNivel_acesso("instrutor");
            instrutorModel.setPlano(null);
            instrutorModel.setPlano_expira_em(null);

            InstrutorModel instrutorModel2 = repository.save(instrutorModel);

            resposta.setMensagem("instrutor criado com sucesso");
            resposta.setSucesso(true);
            resposta.setId(instrutorModel2.getId());

            return resposta;
        }catch(Exception e){
            resposta.setMensagem("erro: "+ e);
            resposta.setSucesso(false);
            return resposta;
        }
    }
}
