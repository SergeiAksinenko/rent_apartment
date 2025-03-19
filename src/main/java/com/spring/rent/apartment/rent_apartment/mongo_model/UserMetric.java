package com.spring.rent.apartment.rent_apartment.mongo_model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "user_metrics")
public class UserMetric {

    @Id
    private String id;

    private String userId;

    private LocalDateTime registerDate;

    private List<String> bookedApartments;

    private LocalDateTime lastLoginDate;

    private Long onlineDuration;

    private LocalDateTime logoutTime;

    private List<String> availableDiscounts;
}
