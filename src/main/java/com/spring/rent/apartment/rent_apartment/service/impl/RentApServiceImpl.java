package com.spring.rent.apartment.rent_apartment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.LocationInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.WeatherInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.RatingEntity;
import com.spring.rent.apartment.rent_apartment.mapper.RentApartmentMapper;
import com.spring.rent.apartment.rent_apartment.repository.AddressRepository;
import com.spring.rent.apartment.rent_apartment.repository.RatingRepository;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRepository;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import com.spring.rent.apartment.rent_apartment.service.IntegrationWeatherService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.APARTMENT_NOT_FOUND;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.SAVE_APARTMENT_SUCCESSFULLY;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RentApServiceImpl implements RentApService {

    public final RentApartmentMapper mapper;

    public final AddressRepository addressRepository;

    public final ApartmentRepository apartmentRepository;

    public final RatingRepository ratingRepository;

    public final IntegrationService integrationService;

    private final IntegrationWeatherService integrationWeatherService;

    public static final String APARTMENT_IS_EXIST = "Апартаменты уже зарегистрированы";

    public static final String COMMENT_IS_DONE = "Спасибо за отзыв!";

    public String saveRegApartment(RentApartmentDto rentApartmentDto) {
        AddressEntity existingAddress = addressRepository.findByCityAndStreetNative(rentApartmentDto.getCity(), rentApartmentDto.getStreet());
        if (!isNull(existingAddress)) {
            return APARTMENT_IS_EXIST;
        }
        ApartmentEntity apartment = mapper.dtoToApartmentEntity(rentApartmentDto);

        AddressEntity address = mapper.dtoToAddressEntity(rentApartmentDto);

        apartmentRepository.save(apartment);

        address.setApartment(apartment);

        addressRepository.save(address);

        return SAVE_APARTMENT_SUCCESSFULLY;
    }

    @Override
    public String addCommentToApartment(RatingDto rating) {
        ApartmentEntity apartment = apartmentRepository.findById(rating.getApartmentId())
                .orElseThrow(() -> new RuntimeException(APARTMENT_NOT_FOUND));

        RatingEntity ratingEntity = mapper.DtoToRatingEntity(rating,
                apartment,
                LocalDateTime.now());

        ratingRepository.save(ratingEntity);

        if (ratingEntity.getApartment() != null) {
            calculateGlobalRating(ratingEntity.getApartment());
        }
        return COMMENT_IS_DONE;
    }

    private void calculateGlobalRating(ApartmentEntity apartment) {
        List<RatingEntity> rating = ratingRepository.findRatingEntitiesByApartmentJpql(apartment);
        int ratingSum = 0;
        for (RatingEntity ratings : rating) {
            ratingSum += ratings.getRating();
        }
        double globalRating = (double) ratingSum / rating.size();
        apartment.setGlobalRating(globalRating);

        apartmentRepository.save(apartment);
    }

    public RentApartmentDto findApartment(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Апартаменты недоступны"));
        return mapper.entityToApartmentDto(apartment, apartment.getAddress());
    }

    @Override
    public List<RentApartmentDto> getInfoByLocation(LocationInfoDto locationInfoDto) {
        integrationService.integrationWithGeocoder(locationInfoDto);
        return null;
    }

    @Override
    public String getInfoByWeather(WeatherInfoDto weatherInfoDto) throws JsonProcessingException {
        return integrationWeatherService.integrationWeatherCode(weatherInfoDto);
    }
}

