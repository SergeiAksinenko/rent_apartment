package com.spring.rent.apartment.rent_apartment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель апартаментов для регистрации")
public class RentApartmentDto {

    @Schema(description = "улица",example = "test")
    private String street;

    @Schema(description = "улица",example = "test")
    private String city;
    @Schema(description = "улица",example = "test")
    private int postalCode;
    @Schema(description = "улица",example = "test")
    private int numberOfRooms;
    @Schema(description = "улица",example = "test")
    private int area;
    @Schema(description = "улица",example = "test")
    private int pricePerNight;
    @Schema(description = "улица",example = "test")
    private String startDate;
    @Schema(description = "улица",example = "test")
    private String endDate;
    @Schema(description = "улица",example = "test")
    private int totalGuest;
    @Schema(description = "улица",example = "test")
    private String availability;
    @Schema(description = "улица",example = "test")
    private String description;
}

