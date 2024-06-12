package com.spring.rent.apartment.rent_apartment.responses;

import lombok.Data;

@Data
public class InvalidLoginResponse {

    private String login;
    private String password;

    public InvalidLoginResponse() {
        login  = "Неверный логин";
        password = "Неверный пароль";
    }
}
