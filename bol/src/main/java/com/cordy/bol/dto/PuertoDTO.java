package com.cordy.bol.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PuertoDTO {

    private int id_Puerto;

    private String nombre_puerto;

    private int Tarifa_hora;

    private int Tarifa_eslora;

    private int disponibilidad;
}
