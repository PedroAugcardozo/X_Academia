package com.academia.projetoacademia.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@Table(name = "endereco")
@AllArgsConstructor
public class enderecoModel {
    @Id
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
    private usuarioModel usuarioModel;
}
