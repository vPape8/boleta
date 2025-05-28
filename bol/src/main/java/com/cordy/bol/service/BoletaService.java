
package com.cordy.bol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cordy.bol.model.Boleta;
import com.cordy.bol.model.Buque;
import com.cordy.bol.model.Funcionario;
import com.cordy.bol.model.Puerto;
import com.cordy.bol.model.calculoHoras;
import com.cordy.bol.repository.BoletaRepository;
import com.cordy.bol.repository.BuqueRepository;
import com.cordy.bol.repository.FuncionarioRepository;
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
    @Autowired
    private FuncionarioRepository funcionarioRepository;    


    public double calcular(String codBuque, Integer id_puerto,Integer id_funcionario) {
        Buque buque = buqueRepository.findById(codBuque)
                .orElseThrow(() -> new IllegalArgumentException("Buque no encontrado"));
        Puerto puerto = puertoRepository.
        findById(id_puerto).orElseThrow(() -> new IllegalArgumentException("Puerto no encontrado"));

        long horasEnPuerto = calculoHoras.calcularHorasEnPuerto(buque.getFecha_llegada(), buque.getFecha_partida());

        double tarifaEslora = puerto.getTarifa_hora() > 0
                ? puerto.getTarifa_eslora()
                : puerto.getTarifa_hora();

        return (tarifaEslora * buque.getEslora()) + (puerto.getTarifa_hora() * horasEnPuerto);
    }

        public Boleta crearYGuardarBoleta(String codBuque, Integer idPuerto, Integer id_Funcionario) {
        Buque buque = buqueRepository.findById(codBuque)
                .orElseThrow(() -> new IllegalArgumentException("Buque no encontrado: " + codBuque));
        Puerto puerto = puertoRepository.findById(idPuerto)
                .orElseThrow(() -> new IllegalArgumentException("Puerto no encontrado: " + idPuerto));
        Funcionario funcionario = funcionarioRepository.findById(id_Funcionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario no encontrado: " + id_Funcionario));

       
        long horasEnPuerto = calculoHoras.calcularHorasEnPuerto(buque.getFecha_llegada(), buque.getFecha_partida());
        double tarifaEslora = puerto.getTarifa_hora() > 0 
                ? puerto.getTarifa_eslora() 
                : puerto.getTarifa_hora();
        double costo = (tarifaEslora * buque.getEslora()) + (puerto.getTarifa_hora() * horasEnPuerto);

        Boleta boleta = new Boleta();
        boleta.setFuncionario(funcionario); 
        boleta.setIdBoleta(codBuque + "-" + idPuerto + "-" + id_Funcionario); 
        boleta.setMonto(costo);
        boleta.setFecha_emision(new java.sql.Date(System.currentTimeMillis()));

        return boletaRepository.save(boleta);

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

