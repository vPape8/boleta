package com.cordy.bol.model;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "Puerto")
@NoArgsConstructor
@AllArgsConstructor

public class Puerto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuerto;

    @Column
    private String nombrePuerto;

    @Column(nullable=false)
    private float tarifaHora;

    @Column
    private float tarifaEslora;

    @Column(nullable=false)
    private boolean dispo;

}