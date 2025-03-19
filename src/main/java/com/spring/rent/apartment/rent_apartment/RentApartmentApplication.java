package com.spring.rent.apartment.rent_apartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class RentApartmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentApartmentApplication.class, args);
    }

}
