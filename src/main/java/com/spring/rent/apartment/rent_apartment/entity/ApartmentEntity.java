package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "apartment_info")
@NoArgsConstructor
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name="apartment_infoSequence", sequenceName="apartment_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="apartment_infoSequence")
    @Column(name = "id")
    private Long id;

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
    private LocalDateTime registrationDate;

    @Column(name = "global_rating")
    private Double globalRating;

    @OneToOne(mappedBy = "apartment")
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "apartment")
    private List<RatingEntity> ratingEntity;

    public ApartmentEntity(int numberOfRooms, int area, int pricePerNight, String startDate, String endDate,
                           int totalGuest, String availability, String description, LocalDateTime registrationDate,
                           Double globalRating, AddressEntity address, List<RatingEntity> ratingEntity) {
        this.numberOfRooms = numberOfRooms;
        this.area = area;
        this.pricePerNight = pricePerNight;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalGuest = totalGuest;
        this.availability = availability;
        this.description = description;
        this.registrationDate = registrationDate;
        this.globalRating = globalRating;
        this.address = address;
        this.ratingEntity = ratingEntity;
    }
}
