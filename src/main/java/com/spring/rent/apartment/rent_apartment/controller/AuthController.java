package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTRATION)
    public String registration(@RequestBody UserRegistrationInfoDto userInfo) {

        return authService.getRegistration(userInfo);
    }

    @PostMapping(AUTHORIZATION)
    public String getAuth(@RequestBody UserAuthInfoDto userInfo) {

        return authService.getAuthorization(userInfo);
    }

    @PostMapping(LOGOUT)
    public String getLogOut(@RequestBody UserRegistrationInfoDto userInfo) {

        return authService.getLogOut(userInfo);
    }
}