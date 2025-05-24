package com.cordy.bol.repository;

import com.cordy.bol.model.Buque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BuqueRepository {

    @Autowired
    @Qualifier("buqueJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Buque findById(String codBuque) {
        String sql = "SELECT cod_buque, nombre_buque, eslora, fecha_llegada, fecha_salida FROM buque WHERE cod_buque = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{codBuque},
                    (rs, rowNum) -> new Buque(
                            rs.getString("cod_buque"),
                            rs.getString("nombre_buque"),
                            rs.getDouble("eslora"),
                            rs.getDate("fecha_llegada"),
                            rs.getDate("fecha_salida")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}