package com.academia.projetoacademia.Controll;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.academia.projetoacademia.DTO.LoginDto;
import com.academia.projetoacademia.DTO.RespostaDto;
import com.academia.projetoacademia.DTO.ResponseDTO;
import com.academia.projetoacademia.configuracaoSeguranca.TokenService;
import com.academia.projetoacademia.configuracaoSeguranca.usuarioAutenticado;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<RespostaDto> login(@RequestBody @Valid LoginDto request) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
            );
            usuarioAutenticado usuario = (usuarioAutenticado) authentication.getPrincipal();
            String token = tokenService.gerarToken(usuario);
            RespostaDto response = new RespostaDto(
                    token,
                    "Bearer",
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getNivelAcesso()
            );
            return ResponseEntity.ok(response);        
        }catch(Exception e){
            e.printStackTrace();
        }
        RespostaDto respostaDto = new RespostaDto(null, null, null, null, null);
        return ResponseEntity.status(500).body(respostaDto); 
    }
}