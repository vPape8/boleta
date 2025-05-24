package com.cordy.bol.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Puerto {

    private int id_Puerto;

    private String nombre_puerto;

    private int Tarifa_hora;

    private int Tarifa_eslora;

    private int disponibilidad;
}
