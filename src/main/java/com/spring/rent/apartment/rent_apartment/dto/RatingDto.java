package com.spring.rent.apartment.rent_apartment.dto;

import lombok.Data;

@Data
public class RatingDto {

    private Long apartmentId;

    private String comments;

    private Integer rating;
}
