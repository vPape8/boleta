package com.cordy.bol.service;

import com.cordy.bol.model.Puerto;
import com.cordy.bol.repository.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class PuertoService {

    @Autowired
    private PuertoRepository puertoRepository;

    public Puerto puertoDB(Long id_puerto){
        return puertoRepository.findById(id_puerto);
    }
}
