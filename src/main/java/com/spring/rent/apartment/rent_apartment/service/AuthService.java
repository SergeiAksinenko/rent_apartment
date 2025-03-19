package com.spring.rent.apartment.rent_apartment.service;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;

public interface AuthService {

    public String getRegistration(UserRegistrationInfoDto registrationInfoDto);

    public String getAuthorization(UserAuthInfoDto authorizationInfoDTO);

    public String getLogOut(UserRegistrationInfoDto logOutInfoDto);

    public UserApplicationEntity tokenValid(String token);

    public String getUsernameFromToken(String token);
}
