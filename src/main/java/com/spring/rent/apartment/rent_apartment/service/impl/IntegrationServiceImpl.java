package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    public final String GEO_URL = "https://api.opencagedata.com/geocode/v1/json?q=%s+%s&key=%s";

    public final String KEY = "e3d785959bbe41a780d17c83b8aa8a6e";

    @Override
    public String integrationWithGeocoder(LocationInfoDto locationInfoDto) {

        RestTemplate restTemplate = new RestTemplate();
        String resultInfoByLocation = restTemplate.exchange(prepareUrl(locationInfoDto),
                HttpMethod.GET,
                new HttpEntity<>(null,null),
                String.class).getBody();

        return resultInfoByLocation;
    }

    private String prepareUrl(LocationInfoDto locationInfoDto) {
        return String.format(GEO_URL, locationInfoDto.getLatitude(), locationInfoDto.getLongitude(), KEY);
    }
}
