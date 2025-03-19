package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "product_info")
public class ProductInfoEntity {

    @Id
    @SequenceGenerator(name="product_infoSequence", sequenceName="product_info_sequence",initialValue = 17, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="product_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "discount_description")
    private String discountDescription;

    @Column(name = "discount_percentage", precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    @Column(name = "applicable_to")
    private String applicableTo;
}
