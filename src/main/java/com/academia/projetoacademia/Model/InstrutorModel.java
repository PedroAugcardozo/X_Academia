package com.academia.projetoacademia.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "instrutor")
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "id_usuario")
@EqualsAndHashCode(callSuper = true)
public class InstrutorModel extends UsuarioModel{
    @Column
    private String cref;
}
