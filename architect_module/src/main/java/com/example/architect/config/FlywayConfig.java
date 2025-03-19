package com.example.architect.config;



import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5435/apartment_db", "admin", "admin")
                .locations("classpath:db/migration", "filesystem:/Users/a1/Documents/Java_Program/rent_apartment/architect_module/src/main/resources/db/migration")
                .load();
        flyway.migrate();
        return flyway;
    }
}
