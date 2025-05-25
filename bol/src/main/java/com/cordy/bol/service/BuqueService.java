package com.cordy.bol.service;

import com.cordy.bol.dto.BuqueDTO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service

public class BuqueService {

    @Value("${servicios.buque.url}")
    private String buqueServiceUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public BuqueService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BuqueDTO buqueDB(String cod_Buque){
       try {
            return restTemplate.getForObject(
                    buqueServiceUrl + "/buques/" + cod_Buque,
                    BuqueDTO.class
            );
       }catch (RestClientException e){
            log.error("Error al obtener informacion del buque: {}", e.getMessage());
            throw new RuntimeException("Error al obtener informacion del buque", e);
           }
    }
}
