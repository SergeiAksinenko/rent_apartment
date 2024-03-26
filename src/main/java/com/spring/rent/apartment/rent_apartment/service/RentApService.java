package com.spring.rent.apartment.rent_apartment.service;

import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;

public interface RentApService {

    public String saveRegApartment(RentApartmentDto rentApartmentDto);

    public String addCommentToApartment(RentApartmentDto rentApartmentDto);
}
