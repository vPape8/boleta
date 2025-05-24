package com.cordy.bol.config;

import jakarta.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class BuqueDBConfig {

    @Bean(name = "buqueJdbcTemplate")
    public JdbcTemplate buqueJdbcTemplate(@Qualifier("buqueDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}