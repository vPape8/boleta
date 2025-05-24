package com.cordy.bol.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="boleta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Boleta {

    @Id
    private String idBoleta;

    @Column(name="monto")
    private double monto;

    @Column(name="fecha_emision")
    private Date fecha_emision;

}
