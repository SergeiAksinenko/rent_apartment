package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address_info")
public class AddressEntity {

    @Id
    @SequenceGenerator(name="address_infoSequence", sequenceName="address_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="address_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

}
