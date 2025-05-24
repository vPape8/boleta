package com.cordy.bol.config;

import jakarta.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "boletaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.boleta")
    public DataSource boletaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "buqueDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.buque")
    public DataSource buqueDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "puertoDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.puerto")
    public DataSource puertoDataSource() {
        return DataSourceBuilder.create().build();
    }
}