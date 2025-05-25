package com.cordy.bol.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BuqueDTO {

    private String cod_Buque;

    private String nombre_buque;

    private double eslora;

    private Date fecha_llegada;

    private Date fecha_salida;
}
