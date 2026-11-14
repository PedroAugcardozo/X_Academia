package com.academia.projetoacademia.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@Table(name = "endereco")
@AllArgsConstructor
public class EnderecoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String rua;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String numero;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuarioModel;

    EnderecoModel(){}
}
