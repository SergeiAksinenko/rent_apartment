package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "rent_assess_info")
@NoArgsConstructor
public class ApartmentRatingEntity {

    @Id
    @SequenceGenerator(name = "rent_assess_infoSequence", sequenceName = "rent_assess_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_assess_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "comments")
    private String comments;

    @Column(name = "rating")
    private  int rating;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartmentRatingEntity;


    public ApartmentRatingEntity(String comments, int rating) {
        this.comments = comments;
        this.rating = rating;
        this.registrationDate = LocalDateTime.now();
    }
}