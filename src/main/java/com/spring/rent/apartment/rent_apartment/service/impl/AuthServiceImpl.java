package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.ValidationFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.AUTH_NOT_FOUND_USER;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.NOT_VALID_PASSWORD;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ValidationFieldService validFieldService;

    @Override
    public String getRegistration(UserRegistrationInfoDto registrationInfoDto) {
        UserApplicationEntity userByNickName = userRepository.getUserApplicationEntityByNickName(registrationInfoDto.getNickName());

        if (!isNull(userByNickName)) {
            return "Такой пользователь существует в системе";
        }

        UserApplicationEntity userByLogin = userRepository.getUserApplicationEntityByLogin(registrationInfoDto.getLogin());
        if (!isNull(userByLogin)) {
            return "пользователь с таким логином существует";
        }
        if (!validFieldService.isValidPassword(registrationInfoDto.getPassword())) {
            return "пароль должен содержать не менее 8-ми символов";
        }
        if (!validFieldService.isValidLogin(registrationInfoDto.getLogin())) {
            return "логин должен содержать символ '@'";
        }

        userRepository.save(prepareUser(registrationInfoDto));
        return "успешная регистрация";
    }

    public UserApplicationEntity prepareUser(UserRegistrationInfoDto registrationInfoDto) {
        return new UserApplicationEntity(registrationInfoDto.getNickName(),
                registrationInfoDto.getLogin(),
                registrationInfoDto.getPassword());
    }

    public String getAuthorization(UserAuthInfoDto userAuthInfoDto) {
        UserApplicationEntity user = userRepository.getUserApplicationEntityByLogin(userAuthInfoDto.getLogin());
        if (isNull(user)) {
            return AUTH_NOT_FOUND_USER;
        }
        if (!userAuthInfoDto.getPassword().equals(user.getPassword())) {
            return NOT_VALID_PASSWORD;
        }

        String token = tokenGeneration();


        user.setToken(token);

        userRepository.save(user);

        return token;
    }

    public String tokenGeneration() {
        return UUID.randomUUID() + "|" + LocalDateTime.now().plusDays(1L);

    }

    @Override
    public String getLogOut(UserRegistrationInfoDto logOutInfoDto) {
        UserApplicationEntity user = userRepository.getUserApplicationEntityByLogin(logOutInfoDto.getLogin());
        if (!isNull(user)) {
            user.setToken(null);
            userRepository.save(user);
            return "Вы успешно вышли из программы";
        } else {
            return "Пользователь не найден";
        }
    }
    public void tokenValid(String token){
        UserApplicationEntity user = userRepository.getUserApplicationEntityByToken(token);
        if (isNull(user)){
            throw new RuntimeException("Войдите в систему");
        }
    }
}
