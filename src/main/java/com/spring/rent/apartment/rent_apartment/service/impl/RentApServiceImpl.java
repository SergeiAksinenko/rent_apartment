package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.RatingEntity;
import com.spring.rent.apartment.rent_apartment.mapper.RentApartmentMapper;
import com.spring.rent.apartment.rent_apartment.repository.AddressRepository;
import com.spring.rent.apartment.rent_apartment.repository.RatingRepository;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRepository;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.APARTMENT_NOT_FOUND;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.SAVE_APARTMENT_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class RentApServiceImpl implements RentApService {

    public final RentApartmentMapper mapper;

    public final AddressRepository addressRepository;

    public final ApartmentRepository apartmentRepository;

    public final RatingRepository ratingRepository;

    public String saveRegApartment(RentApartmentDto rentApartmentDto) {
        ApartmentEntity apartment = mapper.DtoToApartmentEntity(rentApartmentDto);

        AddressEntity address = mapper.DtoToAddressEntity(rentApartmentDto);

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

        if(ratingEntity.getApartment() != null) {
            calculateGlobalRating(ratingEntity.getApartment());
        }
        return "Спасибо за отзыв!";
    }

    private void calculateGlobalRating(ApartmentEntity apartment) {
        List<RatingEntity> rating = ratingRepository.findRatingEntitiesByApartment(apartment);
        int ratingSum = 0;
        for (RatingEntity ratings : rating) {
            ratingSum += ratings.getRating();
        }
        double globalRating = (double) ratingSum / rating.size();
        apartment.setGlobalRating(globalRating);

        apartmentRepository.save(apartment);
    }
}

