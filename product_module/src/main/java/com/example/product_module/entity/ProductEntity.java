package com.example.product_module.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
@Table(name = "product_info")
public class ProductEntity {

    @Id
    private Long id;

    @Column(name = "discount_description")
    private String description;

    @Column(name = "discount_percentage")
    private BigInteger percentage;

    @Column(name = "applicable_to")
    private String appTo;
}
