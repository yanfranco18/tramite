package com.tramite.infrastructure.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.tramite.infrastructure.repository",
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*\\.product\\..*"
        ),
        entityManagerFactoryRef = "tramitesEntityManagerFactory",
        transactionManagerRef = "tramitesTransactionManager"
)
public class TramitesJpaConfig {
}
