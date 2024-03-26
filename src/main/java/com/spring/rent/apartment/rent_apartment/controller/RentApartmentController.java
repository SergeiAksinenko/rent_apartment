package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {

    private final RentApService rentService;

    @PostMapping(BASE_PATH_RENT)
    public String saveNewApartment(@RequestBody RentApartmentDto apartmentDto) {
        return rentService.saveRegApartment(apartmentDto);
    }

    @PostMapping(RATING_RENT_APARTMENT)
    public String addCommentToApartment(@RequestBody RentApartmentDto apartmentDto){
        return  rentService.addCommentToApartment(apartmentDto);
    }
}
