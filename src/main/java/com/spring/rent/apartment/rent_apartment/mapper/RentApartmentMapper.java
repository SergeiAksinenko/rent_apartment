package com.spring.rent.apartment.rent_apartment.mapper;


import com.spring.rent.apartment.rent_apartment.dto.BookingRequestDto;
import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.*;
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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startBooking", source = "start")
    @Mapping(target = "endBooking", source = "end")
    @Mapping(target = "bookingTime", expression = "java(java.time.LocalDateTime.now())") // установка текущего времени
    @Mapping(target = "apartment", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "product", ignore = true)
    public BookingApartmentEntity dtoToBookingApartmentEntity(BookingRequestDto bookingRequestDto);

    default Long convertLocalDateTimeToLong(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.toEpochSecond(java.time.ZoneOffset.UTC) : null;
    }
}
