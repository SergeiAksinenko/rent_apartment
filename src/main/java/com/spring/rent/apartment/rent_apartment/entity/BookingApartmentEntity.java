package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "booking_info")
public class BookingApartmentEntity {

    @Id
    @SequenceGenerator(name = "booking_infoSequence", sequenceName = "booking_info_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_infoSequence")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    @Column(name = "start_booking")
    private LocalDateTime startBooking;

    @Column(name = "end_booking")
    private LocalDateTime endBooking;

    @Column(name = "total_sum_booking")
    private Long totalSumBooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_apartment", referencedColumnName = "id")
    private ApartmentEntity apartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private UserApplicationEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product", referencedColumnName = "id")
    private ProductInfoEntity product;
}
