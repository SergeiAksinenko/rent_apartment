package com.spring.rent.apartment.rent_apartment.mapper;


import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.RatingEntity;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentApartmentMapper {

    public ApartmentEntity dtoToApartmentEntity(RentApartmentDto rentApartmentDto);

    public AddressEntity dtoToAddressEntity(RentApartmentDto rentApartmentDto);

    @Mapping(target = "registrationDate", source = "dateValue")
    @Mapping(target = "apartment", source = "apartmentEntity")
    @Mapping(target = "id", ignore = true)
    public RatingEntity DtoToRatingEntity(RatingDto ratingDto, ApartmentEntity apartmentEntity,
                                          LocalDateTime dateValue);

    public RentApartmentDto entityToApartmentDto(ApartmentEntity apartment, AddressEntity address);

    public UserApplicationEntity registrationDtoToEntity(UserRegistrationInfoDto registrationInfoDto);
}
