package com.spring.rent.apartment.rent_apartment.dao;

import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;


public interface ValidTokenDao {

    public UserApplicationEntity findUserByToken(String token);
}
