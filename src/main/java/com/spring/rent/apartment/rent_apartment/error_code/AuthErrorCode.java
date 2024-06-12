package com.spring.rent.apartment.rent_apartment.error_code;

import lombok.Getter;

@Getter
public enum AuthErrorCode {

    ERROR_600("Ошибка 600: пользователь не найден"),
    ERROR_601("Ошибка 601: необходимо пройти авторизацию"),
    ERROR_602("Ошибка 602: пользователь с таким логином не найден");

    private String message;

    AuthErrorCode(String message) {
        this.message = message;
    }
}
