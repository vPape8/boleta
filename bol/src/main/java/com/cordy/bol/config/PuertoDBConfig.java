package com.cordy.bol.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.cordy.bol.repository.puerto",
        entityManagerFactoryRef = "puertoEntityManagerFactory",
        transactionManagerRef = "puertoTransactionManager"
)
public class PuertoDBConfig {

    @Bean(name = "puertoJdbcTemplate")
    public JdbcTemplate puertoJdbcTemplate(@Qualifier("puertoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean puertoEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("puertoDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.cordy.bol.model.puerto")
                .persistenceUnit("puertoUnit")
                .build();
    }

    @Bean
    public PlatformTransactionManager puertoTransactionManager(
            @Qualifier("puertoEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}