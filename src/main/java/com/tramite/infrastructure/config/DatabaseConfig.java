package com.tramite.infrastructure.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    // ===============================
    // TRAMITES DATABASE CONFIGURATION (Principal)
    // ===============================

    @Bean(name = "tramitesDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.tramites")
    public DataSource tramitesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tramitesEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean tramitesEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("tramitesDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);

        return builder
                .dataSource(dataSource)
                .packages("com.tramite.domain.entity")
                .persistenceUnit("tramites")
                .properties(properties)
                .build();
    }

    @Bean(name = "tramitesTransactionManager")
    @Primary
    public PlatformTransactionManager tramitesTransactionManager(
            @Qualifier("tramitesEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // ===============================
    // PRODUCTS DATABASE CONFIGURATION (Secundaria)
    // ===============================

    @Bean(name = "productsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.products")
    public DataSource productsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "productsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("productsDataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", true);

        return builder
                .dataSource(dataSource)
                .packages("com.tramite.domain.product")
                .persistenceUnit("products")
                .properties(properties)
                .build();
    }

    @Bean(name = "productsTransactionManager")
    public PlatformTransactionManager productsTransactionManager(
            @Qualifier("productsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
