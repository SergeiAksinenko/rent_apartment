package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "statistic_info")
@NoArgsConstructor
public class StatisticInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistic_info_seq")
    @SequenceGenerator(name = "statistic_info_seq", sequenceName = "statistic_info_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "views_count")
    private Long viewsCount = 0L;

    @Column(name = "booking_count")
    private Long bookingCount = 0L;

    @Column(name = "total_sum_revenue")
    private long totalSumRevenue = 0L;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;

}
