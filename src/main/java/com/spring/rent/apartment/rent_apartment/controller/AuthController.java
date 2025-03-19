package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "apartment_module",description = "API для управления модулем 'rent_apartment'")
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTRATION)
    @Operation(summary = "Регистрация пользователя")
    @Parameter(name = "nickName,login",description = "никнейм и логин пользователя", example = "Test,Test@gmail.com") //уточнить
    public String registration(@RequestBody UserRegistrationInfoDto userInfo) {

        return authService.getRegistration(userInfo);
    }

    @PostMapping(AUTHORIZATION)
    @Operation(summary = "авторизация пользователя")
    @Parameter(name = "login",description = "логин пользователя", example = "Test@gmail.ru")
    public String getAuth(@RequestBody UserAuthInfoDto userInfo) {

        return authService.getAuthorization(userInfo);
    }

    @PostMapping(LOGOUT)
    @Operation(summary = "выход пользователя из системы")
    @Parameter(name = "login",description = "логин пользователя", example = "Test@gmail.ru")
    public String getLogOut(@RequestBody UserRegistrationInfoDto userInfo) {

        return authService.getLogOut(userInfo);
    }
}