package com.cordy.bol.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cordy.bol.model.Buque;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class BuqueService {

    @Value("${servicios.buque.url}")
    private String buqueServiceUrl;

    private final RestTemplate restTemplate;


    public Buque buqueDB(String cod_Buque){
       try {
            return restTemplate.getForObject(
                    buqueServiceUrl + "/buques/" + cod_Buque,
                    Buque.class
            );
       }catch (RestClientException e){
            log.error("Error al obtener informacion del buque: {}", e.getMessage());
            throw new RuntimeException("Error al obtener informacion del buque", e);
           }
    }
}
