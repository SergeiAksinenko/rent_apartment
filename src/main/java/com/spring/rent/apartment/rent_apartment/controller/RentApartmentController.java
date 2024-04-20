package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.RatingDto;
import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class RentApartmentController {

    private final RentApService rentService;

    private  final AuthService authService;

    @PostMapping(ADD_NEW_APARTMENT)
    public String saveNewApartment(@RequestBody RentApartmentDto apartmentDto,
                                   @RequestHeader String token) {
        authService.tokenValid(token);
        return rentService.saveRegApartment(apartmentDto);
    }

    @PostMapping(ADD_NEW_COMMENT)
    public String addCommentToApartment(@RequestBody RatingDto ratingDto){
      return rentService.addCommentToApartment(ratingDto);
    }
    @PostMapping(FIND_APARTMENT)
    public RentApartmentDto findApartment(@RequestParam Long id){
        return rentService.findApartment(id);
    }
}
