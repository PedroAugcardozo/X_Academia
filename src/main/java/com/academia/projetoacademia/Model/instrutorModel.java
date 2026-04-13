package com.academia.projetoacademia.Model;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class instrutorModel extends usuarioModel{
    @Column
    private String cref;
}
