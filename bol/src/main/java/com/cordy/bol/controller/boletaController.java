package com.cordy.bol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cordy.bol.model.Boleta;
import com.cordy.bol.service.BoletaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/boleta")
@RequiredArgsConstructor
public class boletaController {

    @Autowired
    private final BoletaService boletaService;

    @GetMapping("/GetCalculo/")
    public ResponseEntity<Double> calculoService(
            @RequestParam(name = "cod_buque", required = true)String codBuque,
            @RequestParam(name = "id_puerto", required = true)Integer idPuerto
    ) { log.info("cod_buque: {}, id_puerto: {}", codBuque, idPuerto);
        try {
            double costo = boletaService.calcular(codBuque, idPuerto);
            return ResponseEntity.ok(costo);
        } catch (Exception e) {
            log.error("Error en calculoService", e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/GetBoletas/")
    public  ResponseEntity<List<Boleta>> listar(){
        List<Boleta> boletas = boletaService.findAll();
        if (boletas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @PostMapping("/PostCalculo/")
    public ResponseEntity<Boleta> guardarCalculo(
            @RequestParam(name = "cod_buque", required = true) String codBuque,
            @RequestParam(name = "id_puerto", required = true) Integer idPuerto
    ) {
        log.info("Guardando cálculo para cod_buque: {}, id_puerto: {}", codBuque, idPuerto);
        try {
            double costo = boletaService.calcular(codBuque, idPuerto);

            // Crear una nueva instancia de Boleta
            Boleta boleta = new Boleta();
            boleta.setIdBoleta(codBuque + "-" + idPuerto); // Generar un ID único
            boleta.setMonto(costo);
            boleta.setFecha_emision(new java.sql.Date(System.currentTimeMillis()));

            // Guardar en la base de datos
            Boleta savedBoleta = boletaService.save(boleta);

            return ResponseEntity.ok(savedBoleta);
        } catch (Exception e) {
            log.error("Error al guardar cálculo", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
