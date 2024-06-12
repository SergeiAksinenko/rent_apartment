package com.spring.rent.apartment.rent_apartment.error_code;

import lombok.Getter;

@Getter
public enum IntegrationErrorCode {

    ERROR_800("Ошибка 800: в базе данных отсутствуют параметры для интеграции");

    private String message;

    IntegrationErrorCode(String message) {
        this.message = message;
    }
}