package com.spring.rent.apartment.rent_apartment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.geocoder_response.Components;
import com.spring.rent.apartment.rent_apartment.dto.geocoder_response.GeocoderResponse;
import com.spring.rent.apartment.rent_apartment.dto.geocoder_response.ResultsObject;
import com.spring.rent.apartment.rent_apartment.entity.BookingApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.IntegrationEntity;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.IntegrationParamException;
import com.spring.rent.apartment.rent_apartment.repository.IntegrationRepository;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.spring.rent.apartment.rent_apartment.service.impl.RentApServiceImpl.GEOCODER_EXCEPTION;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationRepository integrationRepository;

    public final RestTemplate restTemplate = new RestTemplate();

    public static final String INTEGRATION_MESSAGE = "в базе данных отсутствуют параметры для интеграции";

    /*********            Geocoder      *********/


    @Override
    public String integrationWithGeocoder(LocationInfoDto locationInfoDto) {

        GeocoderResponse geoCoderResult = restTemplate.exchange(prepareUrl(locationInfoDto, "GEO"),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                GeocoderResponse.class).getBody();

        return checkCityValue(geoCoderResult);
    }

    private String checkCityValue(GeocoderResponse geoCoderResult){
        if(!isNull(geoCoderResult)){
            List<ResultsObject> resultsList = geoCoderResult.getResultsList();
            if(!isNull(resultsList) || resultsList.isEmpty()){
                Components componentsResult = resultsList.get(0).getComponentsResult();
                if(!isNull(componentsResult)){
                    if(!isNull(componentsResult.getCity()))
                        return componentsResult.getCity();
                    if(!isNull(componentsResult.getTown()))
                        return componentsResult.getTown();
                }
            }
        }
        throw new  RuntimeException("Ошибка обработки результата");
    }

    private String prepareUrl(LocationInfoDto locationInfoDto, String idIntegration) {
        IntegrationEntity integrationParam = getIntegrationParam(idIntegration);
        return String.format(integrationParam.getPath(), locationInfoDto.getLatitude(), locationInfoDto.getLongitude(),Base64EncoderDecoder.decode(integrationParam.getKey()));
    }

    private IntegrationEntity getIntegrationParam(String idIntegration){
       return integrationRepository.findById(idIntegration).orElseThrow(() -> new IntegrationParamException(INTEGRATION_MESSAGE,800));
    }


    /*********            WeatherCode      *********/

    @Override
    public String integrationWeatherCode(WeatherInfoDto weatherInfoDto) throws JsonProcessingException {

        String resultInfoByWeather = restTemplate.exchange(prepareUrl(weatherInfoDto,"WEATHER"),
                HttpMethod.GET,
                new HttpEntity<>(null, prepareHeadersForRequest()),
                String.class).getBody();

        return prepareRequestForWeather(resultInfoByWeather);
    }

    @Override
    public void integrationProductModule(String bookingValue) {
        restTemplate.exchange(prepareUrl(bookingValue,"PRODUCT_MODULE"),
                HttpMethod.GET,
                new HttpEntity<>(null,null),
                Void.class).getBody();
    }

    private String prepareRequestForWeather(String allWeatherInfo) throws JsonProcessingException  {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(allWeatherInfo);
        String condition = jsonNode.get("fact").get("condition").asText();
        String temp_avg = jsonNode.get("forecasts").get(0).get("parts").get("day").get("temp_avg").asText();

        String message =  "Погода на время заезда в апартаменты: " + condition + " ," + temp_avg + " градусов";

        return message;
    }

    private String prepareUrl(WeatherInfoDto weatherInfoDto, String idIntegration) {
        IntegrationEntity integrationParam = getIntegrationParam(idIntegration);
        return  String.format(integrationParam.getPath(), weatherInfoDto.getLatitude(), weatherInfoDto.getLongitude(),Base64EncoderDecoder.decode(integrationParam.getKey()));
    }

    private String prepareUrl(String bookingValue, String idIntegration) {
        IntegrationEntity integrationParam = getIntegrationParam(idIntegration);
        return  String.format(integrationParam.getPath(), bookingValue);
    }

    private HttpHeaders prepareHeadersForRequest(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        return httpHeaders;
    }

}
