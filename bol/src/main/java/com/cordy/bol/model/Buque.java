package com.cordy.bol.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buque")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Buque {

    @Id
    private String codBuque;

    @Column(nullable = false)
    private String nombreBuque;

    @Column(nullable = false)
    private float eslora;

    @Column(nullable = false)
    private Date fechaLlegada;

    @Column(nullable = false)
    private Date fechaPartida;
}
