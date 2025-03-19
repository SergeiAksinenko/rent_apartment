package com.spring.rent.apartment.rent_apartment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDto {

    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

}
