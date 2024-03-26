package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentRatingEntity;
import com.spring.rent.apartment.rent_apartment.mapper.RentApartmentMapper;
import com.spring.rent.apartment.rent_apartment.repository.AddressRepository;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRatingRepository;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRepository;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@Service
@RequiredArgsConstructor
public class RentApServiceImpl implements RentApService {

    public final RentApartmentMapper mapper;

    public final AddressRepository addressRepository;

    public final ApartmentRepository apartmentRepository;

    public final ApartmentRatingRepository ratingRepository;

    public String saveRegApartment(RentApartmentDto rentApartmentDto){
        ApartmentEntity apartment = mapper.DtoToApartmentEntity(rentApartmentDto);

        AddressEntity address = mapper.DtoToAddressEntity(rentApartmentDto);

        apartmentRepository.save(apartment);

        address.setApartment(apartment);

        addressRepository.save(address);

        return SAVE_APARTMENT_SUCCESSFULLY;
    }
    public String addCommentToApartment(RentApartmentDto rentApartmentDto){

        ApartmentRatingEntity rating = mapper.DtoToRatingEntity(rentApartmentDto);

        rating.setComments();
    }
}
