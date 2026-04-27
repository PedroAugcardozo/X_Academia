package com.academia.projetoacademia.Model;


import java.time.LocalDate;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class usuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String senha;

    @Column
    private String nome;

    @Column
    private String email;
    
    @Column
    private String cpf;

    @Column
    private String sexo;

    @Column
    private LocalDate data_nasc;

    @Column
    private String nivel_acesso;

    @Column
    private LocalDateTime plano_expira_em;

    @ManyToOne
    @JoinColumn(name = "id_plano")
    private planoModel plano;

    public Long getId(){
        return this.id;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public String getNivelAcesso(){
        return this.nivel_acesso;
    }

    public String getSenha(){
        return this.senha;
    }
}
