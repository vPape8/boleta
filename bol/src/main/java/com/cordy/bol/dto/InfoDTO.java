package com.cordy.bol.dto;

import com.cordy.bol.model.Boleta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoDTO {
    private Boleta boleta;
    private BuqueDTO buque;
    private PuertoDTO puerto;
}
