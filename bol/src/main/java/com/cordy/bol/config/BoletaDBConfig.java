
package com.cordy.bol.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.cordy.bol.repository",
    entityManagerFactoryRef = "boletaEntityManagerFactory",
    transactionManagerRef = "boletaTransactionManager"
)
public class BoletaDBConfig {

    @Primary
    @Bean(name = "boletaDataSource")
    public DataSource boletaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "boletaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean boletaEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("boletaDataSource") DataSource dataSource) {
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.cordy.bol.model")
                .build();
    }

    @Primary
    @Bean(name = "boletaTransactionManager")
    public PlatformTransactionManager boletaTransactionManager(
            @Qualifier("boletaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
