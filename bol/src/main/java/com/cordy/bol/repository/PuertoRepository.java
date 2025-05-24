package com.cordy.bol.repository;

import com.cordy.bol.model.Puerto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class PuertoRepository {

    @Autowired
    @Qualifier("puertoJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Puerto findById(Long idPuerto) {
        String sql = "SELECT id_Puerto, nombre_puerto, Tarifa_hora, Tarifa_eslora, disponibilidad FROM puerto WHERE id_Puerto = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{idPuerto},
                    (rs, rowNum) -> new Puerto(
                            rs.getInt("id_Puerto"),
                            rs.getString("nombre_puerto"),
                            rs.getInt("Tarifa_hora"),
                            rs.getInt("Tarifa_eslora"),
                            rs.getInt("disponibilidad")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}