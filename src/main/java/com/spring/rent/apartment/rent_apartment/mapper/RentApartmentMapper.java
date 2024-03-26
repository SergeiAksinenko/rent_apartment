package com.spring.rent.apartment.rent_apartment.mapper;


import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentRatingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RentApartmentMapper {

    public ApartmentEntity DtoToApartmentEntity(RentApartmentDto rentApartmentDto);

    public AddressEntity DtoToAddressEntity(RentApartmentDto rentApartmentDto);

    public ApartmentRatingEntity DtoToRatingEntity(RentApartmentDto rentApartmentDto);


}
