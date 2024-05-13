package com.spring.rent.apartment.rent_apartment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;

public interface IntegrationWeatherService {

    public String integrationWeatherCode(WeatherInfoDto weatherInfoDto) throws JsonProcessingException;
}
