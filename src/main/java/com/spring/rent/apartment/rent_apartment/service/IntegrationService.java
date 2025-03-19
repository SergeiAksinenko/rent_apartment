package com.spring.rent.apartment.rent_apartment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;

public interface IntegrationService {

    public String  integrationWithGeocoder(LocationInfoDto locationInfoDto);

    public String integrationWeatherCode(WeatherInfoDto weatherInfoDto) throws JsonProcessingException;

    public void integrationProductModule(String bookingValue);
}
