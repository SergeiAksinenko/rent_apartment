package com.spring.rent.apartment.rent_apartment.service;

import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;

public interface IntegrationService {

    public String  integrationWithGeocoder(LocationInfoDto locationInfoDto);
}
