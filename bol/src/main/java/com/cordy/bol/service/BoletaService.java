
package com.cordy.bol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cordy.bol.model.Boleta;
import com.cordy.bol.model.Buque;
import com.cordy.bol.model.Puerto;
import com.cordy.bol.model.calculoHoras;
import com.cordy.bol.repository.BoletaRepository;
import com.cordy.bol.repository.BuqueRepository;
import com.cordy.bol.repository.PuertoRepository;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private PuertoRepository puertoRepository;
    @Autowired
    private BuqueRepository buqueRepository;
    @Autowired
    private BoletaRepository boletaRepository;


    public double calcular(String codBuque, Integer id_puerto) {
        Buque buque = buqueRepository.findById(codBuque)
                .orElseThrow(() -> new IllegalArgumentException("Buque no encontrado"));
        Puerto puerto = puertoRepository.
        findById(id_puerto).orElseThrow(() -> new IllegalArgumentException("Puerto no encontrado"));



        long horasEnPuerto = calculoHoras.calcularHorasEnPuerto(buque.getFechaLlegada(), buque.getFechaPartida());

        double tarifaEslora = puerto.getTarifaHora() > 0
                ? puerto.getTarifaEslora()
                : puerto.getTarifaHora();

        return (tarifaEslora * buque.getEslora()) + (puerto.getTarifaHora() * horasEnPuerto);
    }

    public List<Boleta> findAll(){
        return boletaRepository.findAll();
    }

    public  Boleta findById(String idBoleta){
        return boletaRepository.findById(idBoleta).
                orElseThrow(()-> new RuntimeException("Boleta no encontrada"));
    }
    
    public Boleta save(Boleta boleta) {
        return boletaRepository.save(boleta);
    }
}

