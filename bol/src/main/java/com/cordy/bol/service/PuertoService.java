package com.cordy.bol.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cordy.bol.dto.PuertoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PuertoService {

    @Value("${servicios.puerto.url}")
    private String puertoServiceUrl;

    private final RestTemplate restTemplate;

    public PuertoDTO puertoDB(Integer id_puerto) {
        try {
            return restTemplate.getForObject(
                    puertoServiceUrl + "/puertos/" + id_puerto,
                    PuertoDTO.class
            );
        } catch (RestClientException e) {
            log.error("Error al obtener información del puerto: {}", e.getMessage());
            throw new ServiceException("Error en servicio de puertos", e);
        }
    }
}
