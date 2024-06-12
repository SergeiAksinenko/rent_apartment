package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.service.ValidationFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationFieldServiceImpl implements ValidationFieldService {

    @Override
    public boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

    public boolean isValidLogin(String login){
        return login.contains("@");
    }

}

