package com.spring.rent.apartment.rent_apartment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;

public class TestUtilConst {

    public static LocationInfoDto locationInfoDtoForTest() {

        LocationInfoDto locationInfoDto = new LocationInfoDto();
        locationInfoDto.setLatitude("55.0415");
        locationInfoDto.setLongitude("82.9346");
        return locationInfoDto;
    }

    public static String CITY_LOCATION = "Москва";


    public static RentApartmentDto rentApartmentDtoForTest() throws Exception {
        RentApartmentDto rentApartmentDto = new RentApartmentDto();
        rentApartmentDto.setNumberOfRooms(4);
        rentApartmentDto.setArea(40);
        rentApartmentDto.setPricePerNight(3500);
        rentApartmentDto.setTotalGuest(3);
        rentApartmentDto.setDescription("описание квартиры");
        return rentApartmentDto;
    }

    public static UserRegistrationInfoDto userRegistrationInfoDtoForTest()throws Exception {
        UserRegistrationInfoDto userRegistrationInfoDto = new UserRegistrationInfoDto();
        userRegistrationInfoDto.setNickName("testNick");
        userRegistrationInfoDto.setLogin("testLogin@test.com");
        userRegistrationInfoDto.setPassword("12345678");
        userRegistrationInfoDto.setEmail("testEmail@test.com");
        return userRegistrationInfoDto;
    }

    public static UserApplicationEntity userApplicationEntityForTest()throws Exception {
        UserApplicationEntity user = new UserApplicationEntity();
        user.setNickName("testNick");
        user.setLogin("testLogin@test.com");
        user.setPassword("password");
        user.setEmail("testEmail@test.com");
        return user;
    }

    public static String asJSONstring(final Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
