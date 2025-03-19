package com.spring.rent.apartment.rent_apartment.mongo_model;


import lombok.Data;

@Data
public class ApartmentInfo {

    private String bookingTime;

    private String address;

    private Integer price;
}
