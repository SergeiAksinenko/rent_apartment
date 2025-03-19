package com.spring.rent.apartment.rent_apartment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Общая модель для продуктового модуля")
public class ProductRequestDto {


    private Long totalSumBooking;

    private String login;

    private String nickName;

    private String street;

    private String city;

    private LocalDateTime start;

    private LocalDateTime end;

}
