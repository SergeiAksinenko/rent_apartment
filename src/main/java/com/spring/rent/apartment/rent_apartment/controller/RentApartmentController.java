package com.spring.rent.apartment.rent_apartment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {

    private final RentApService rentService;

    private  final AuthService authService;

    @PostMapping(ADD_NEW_APARTMENT)
    public String saveNewApartment(@RequestBody RentApartmentDto apartmentDto,
                                   @RequestHeader String token) {
        authService.tokenValid(token);
        return rentService.saveRegApartment(apartmentDto);
    }

    @PostMapping(ADD_NEW_COMMENT)
    public String addCommentToApartment(@RequestBody RatingDto ratingDto){
      return rentService.addCommentToApartment(ratingDto);
    }
    @PostMapping(FIND_APARTMENT)
    public RentApartmentDto findApartment(@RequestParam Long id){
        return rentService.findApartment(id);
    }

    @PostMapping(BY_LOCATION)
    public List<RentApartmentDto> findAllApartment(@RequestBody LocationInfoDto locationInfoDto){
        return rentService.getInfoByLocation(locationInfoDto);
    }

    @PostMapping(BY_WEATHER)
    public String findWeatherInformation(@RequestBody WeatherInfoDto weatherInfoDto) throws JsonProcessingException {
        return rentService.getInfoByWeather(weatherInfoDto);
    }
}
