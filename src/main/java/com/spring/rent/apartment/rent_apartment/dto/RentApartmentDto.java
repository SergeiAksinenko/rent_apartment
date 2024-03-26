package com.spring.rent.apartment.rent_apartment.dto;

import lombok.Data;

@Data
public class RentApartmentDto {

    private String street;

    private String city;

    private int postalCode;

    private int numberOfRooms;

    private int area;

    private int pricePerNight;

    private String startDate;

    private String endDate;

    private int totalGuest;

    private String availability;

    private String description;
}

