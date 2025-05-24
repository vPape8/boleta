package com.cordy.bol.service;

import com.cordy.bol.model.Buque;
import com.cordy.bol.repository.BuqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class BuqueService {

    @Autowired
    private BuqueRepository buqueRepository;

    public Buque buqueDB(String cod_buque){
        return buqueRepository.findById(cod_buque);
    }
}
