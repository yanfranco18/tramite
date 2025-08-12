package com.tramite.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tramite.infrastructure.repository",
        entityManagerFactoryRef = "tramitesEntityManagerFactory",
        transactionManagerRef = "tramitesTransactionManager"
)
public class TramitesJpaConfig {
}
