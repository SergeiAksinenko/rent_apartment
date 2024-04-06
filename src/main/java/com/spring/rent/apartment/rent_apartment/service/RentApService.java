package com.spring.rent.apartment.rent_apartment.service;

import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;

public interface RentApService {

    public String saveRegApartment(RentApartmentDto rentApartmentDto);

    public String addCommentToApartment(RatingDto ratingDto);
}
