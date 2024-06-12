package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "integration_info")
@Getter
public class IntegrationEntity {

    @Id
    private String id;

    @Column(name = "path")
    private String path;

    @Column(name = "key")
    private String key;
}
