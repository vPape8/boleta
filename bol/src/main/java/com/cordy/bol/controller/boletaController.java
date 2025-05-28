package com.cordy.bol.controller;

import java.util.List;

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


    private final BoletaService boletaService;

    @GetMapping("/GetCalculo/")
    public ResponseEntity<Double> calculoService(
            @RequestParam(name = "cod_buque", required = true)String codBuque,
            @RequestParam(name = "id_puerto", required = true)Integer idPuerto,
            @RequestParam(name = "id_funcionario", required = true) Integer id 
    ) { log.info("cod_buque: {}, id_puerto: {}, id {}", codBuque, idPuerto,id);
        try {
            double costo = boletaService.calcular(codBuque, idPuerto,id);
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

    @GetMapping("/GetBoletaID/")
    public ResponseEntity<Boleta> getBoletaById(
            @RequestParam(name = "id_boleta", required = true) String idBoleta
    ) {
        log.info("Buscando boleta con id: {}", idBoleta);
        try {
            Boleta boleta = boletaService.findById(idBoleta);
            return ResponseEntity.ok(boleta);
        } catch (RuntimeException e) {
            log.error("Error al buscar boleta por ID", e);
            return ResponseEntity.notFound().build();
        }   
    }

    @PostMapping("/PostBoleta/")
    public ResponseEntity<Boleta> guardarBoleta(
            @RequestParam(name = "cod_buque", required = true) String codBuque,
            @RequestParam(name = "id_puerto", required = true) Integer idPuerto,
            @RequestParam(name = "id_funcionario", required = true) Integer id // id funcionario
    ) {
        log.info("Guardando boleta para cod_buque: {}, id_puerto: {}, id_funcionario: {}", codBuque, idPuerto, id);
        try {
            
            Boleta savedBoleta = boletaService.crearYGuardarBoleta(codBuque, idPuerto, id);

            return ResponseEntity.ok(savedBoleta);
        } catch (IllegalArgumentException e) { 
            log.error("Error al guardar boleta: {}", e.getMessage());
            
            return ResponseEntity.badRequest().build(); 
        } catch (Exception e) { 
            log.error("Error inesperado al guardar boleta", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
