package com.spring.rent.apartment.rent_apartment.dao.impl;

import com.spring.rent.apartment.rent_apartment.dao.ValidTokenDao;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ValidTokenDaoImpl implements ValidTokenDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserApplicationEntity findUserByToken(String token) {
        String query = "SELECT * FROM user_info WHERE token = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{token}, (resultSet, rowNum) -> {
            UserApplicationEntity user = new UserApplicationEntity();
            user.setId(resultSet.getLong("id"));
            user.setNickName(resultSet.getString("nickName"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setRegistrationData(resultSet.getObject("registrationData", LocalDateTime.class));
            user.setToken(resultSet.getString("token"));
            return user;
        });
    }
}
