package com.cordy.bol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cordy.bol.model.Boleta;
import com.cordy.bol.service.BoletaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class boletaController {

    @Autowired
    private final BoletaService boletaService;

    @GetMapping("/calculo")
    public ResponseEntity<Double> calculoService(
            @RequestParam String cod_buque,
            @RequestParam Long id_puerto
    ) {
        try {
            double costo = boletaService.calcular(cod_buque, id_puerto);
            return ResponseEntity.ok(costo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("Boletas")
    public  ResponseEntity<List<Boleta>> listar(){
        List<Boleta> boletas = boletaService.findAll();
        if (boletas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

}
