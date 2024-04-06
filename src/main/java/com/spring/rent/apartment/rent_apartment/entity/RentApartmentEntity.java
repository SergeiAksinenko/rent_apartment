package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rent_apartment_info")
@NoArgsConstructor
public class RentApartmentEntity {

    @Id
    @SequenceGenerator(name="rent_apartment_infoSequence", sequenceName="rent_apartment_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rent_apartment_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private int postalCode;

    @Column(name = "number_of_rooms")
    private int numberOfRooms;

    @Column(name = "area")
    private int area;

    @Column(name = "price_per_night")
    private int pricePerNight;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "total_guest")
    private int totalGuest;

    @Column(name = "availability")
    private String availability;

    @Column(name = "description")
    private String description;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate; // переделать

    public RentApartmentEntity(String address, int numberOfRooms, int postalCode,
                               int totalGuest, int pricePerNight,
                               String availability, String city, String description,
                               String endDate, String startDate, String street) {
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.postalCode = postalCode;
        this.totalGuest = totalGuest;
        this.pricePerNight = pricePerNight;
        this.availability = availability;
        this.city = city;
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
        this.street = street;
        this.registrationDate = LocalDateTime.now();
    }
}
