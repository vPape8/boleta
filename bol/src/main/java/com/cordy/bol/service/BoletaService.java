
package com.cordy.bol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cordy.bol.dto.BuqueDTO;
import com.cordy.bol.dto.PuertoDTO;
import com.cordy.bol.model.Boleta;
import com.cordy.bol.model.calculoHoras;
import com.cordy.bol.repository.BoletaRepository;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private PuertoService puertoService;
    @Autowired
    private BuqueService buqueService;
    @Autowired
    private BoletaRepository boletaRepository;


    public double calcular(String cod_buque, Integer id_puerto) {
        BuqueDTO buque = buqueService.buqueDB(cod_buque);
        PuertoDTO puerto = puertoService.puertoDB(id_puerto);

        if(buque==null || puerto==null){
            throw new IllegalArgumentException("Datos incorrectos");

        }

        long horasEnPuerto = calculoHoras.calcularHorasEnPuerto(buque.getFecha_llegada(), buque.getFecha_salida());

        double tarifaEslora = puerto.getTarifa_eslora() > 0
                ? puerto.getTarifa_eslora()
                : puerto.getTarifa_hora();

        return (tarifaEslora * buque.getEslora()) + (puerto.getTarifa_hora() * horasEnPuerto);
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

