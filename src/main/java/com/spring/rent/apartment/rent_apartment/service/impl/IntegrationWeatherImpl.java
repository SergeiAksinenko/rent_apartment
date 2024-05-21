package com.spring.rent.apartment.rent_apartment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;
import com.spring.rent.apartment.rent_apartment.service.IntegrationWeatherService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IntegrationWeatherImpl implements IntegrationWeatherService {

    public final String WEATHER_URL = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&lang=ru_RU";

    public final String KEY_NAME = "X-Yandex-API-Key";

    public final String KEY_VALUE = "85274ea5-aeb7-4b98-a4ea-769ea5b802a4";

    @Override
    public String integrationWeatherCode(WeatherInfoDto weatherInfoDto) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String resultInfoByWeather = restTemplate.exchange(prepareUrl(weatherInfoDto),
                HttpMethod.GET,
                new HttpEntity<>(null, prepareHeadersForRequest()),
                String.class).getBody();

        return prepareRequestForWeather(resultInfoByWeather);
    }

    private String prepareRequestForWeather(String allWeatherInfo) throws JsonProcessingException  {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(allWeatherInfo);
        String condition = jsonNode.get("fact").get("condition").asText();
        String temp_avg = jsonNode.get("forecasts").get(0).get("parts").get("day").get("temp_avg").asText();

        String message =  "Погода на время заезда в апартаменты: " + condition + " ," + temp_avg + " градусов";

        return message;
    }

    private String prepareUrl(WeatherInfoDto weatherInfoDto){
        return String.format(WEATHER_URL, weatherInfoDto.getLatitude(),weatherInfoDto.getLongitude(), KEY_VALUE);
    }

    private HttpHeaders prepareHeadersForRequest(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(KEY_NAME, KEY_VALUE);
        return httpHeaders;
    }
}
