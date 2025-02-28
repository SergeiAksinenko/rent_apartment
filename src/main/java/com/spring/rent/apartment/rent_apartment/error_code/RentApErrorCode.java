package com.spring.rent.apartment.rent_apartment.error_code;

import lombok.Getter;


@Getter
public enum RentApErrorCode {

    ERROR_700("Ошибка 700: апартаменты не найдены, попробуйте повторить поиск");

    private String message;

    RentApErrorCode(String message) {
        this.message = message;
    }
}
