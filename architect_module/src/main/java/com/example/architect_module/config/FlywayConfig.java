package com.example.architect_module.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:9091/apartment_db","admin","admin")
                .locations("classpath:db/migration", "filesystem:./architect_module/src/main/resources/db/migration")
                .load();
    }
}
