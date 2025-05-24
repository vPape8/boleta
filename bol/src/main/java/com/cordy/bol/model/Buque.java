package com.cordy.bol.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Buque {

    private String cod_Buque;

    private String nombre_buque;

    private double eslora;

    private Date fecha_llegada;

    private Date fecha_salida;
}
