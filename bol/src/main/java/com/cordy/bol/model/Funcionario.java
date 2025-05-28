package com.cordy.bol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true, length = 8, nullable=false)
    private String run;

    @Column(length = 1, nullable=false)
    private String dv;

    @Column(nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String appaterno;

    @Column(nullable=false)
    private String apmaterno;

    @Column
    private String correo;

    @Column
    private String telefono;
}