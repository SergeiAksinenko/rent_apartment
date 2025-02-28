package com.spring.rent.apartment.rent_apartment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;

import java.util.List;

public interface RentApService {

    public String saveRegApartment(RentApartmentDto rentApartmentDto);

    public String addCommentToApartment(RatingDto ratingDto);

    public RentApartmentDto findApartment(Long id);

    public List<RentApartmentDto> getInfoByLocation(LocationInfoDto locationInfoDto);

    public String getInfoByWeather(WeatherInfoDto weatherInfoDto) throws JsonProcessingException;
}
