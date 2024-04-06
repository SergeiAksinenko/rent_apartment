package com.spring.rent.apartment.rent_apartment.mapper;


import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentApartmentMapper {

    public ApartmentEntity DtoToApartmentEntity(RentApartmentDto rentApartmentDto);

    public AddressEntity DtoToAddressEntity(RentApartmentDto rentApartmentDto);

    @Mapping(target = "registrationDate", source = "dateValue")
    @Mapping(target = "apartment", source = "apartmentEntity")
    @Mapping(target = "id", ignore = true)
    public RatingEntity DtoToRatingEntity(RatingDto ratingDto, ApartmentEntity apartmentEntity,
                                          LocalDateTime dateValue);

}
