package com.spring.rent.apartment.rent_apartment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.*;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import com.spring.rent.apartment.rent_apartment.service.impl.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "apartment_module", description = "API для управления модулем 'rent_apartment'")
public class RentApartmentController {

    private final RentApService rentService;

    private final AuthService authService;

    private final PhotoService photoService;

    @PostMapping(ADD_NEW_APARTMENT)
    @Operation(summary = "Регистрация новых апартаментов")
    @Parameter(name = "token", description = "Токен авторизированного пользователя", in = ParameterIn.HEADER, example = "ed35ec58-8212-46fd-915e-d035afbdcd10|2034-04-21T16:35:41.708437")
    public String saveNewApartment(@RequestBody RentApartmentDto apartmentDto,
                                   @RequestHeader String token) {
        authService.tokenValid(token);
        return rentService.saveRegApartment(apartmentDto);
    }

    @PostMapping(ADD_NEW_COMMENT)
    @Operation(summary = "Добавление комментариев к апартаментам")
    @Parameter(name = "id", description = "идентификатор апартаментов", example = "1")
    public String addCommentToApartment(@RequestBody RatingDto ratingDto) {
        return rentService.addCommentToApartment(ratingDto);
    }

    @PostMapping(FIND_APARTMENT)
    @Operation(summary = "Получаем апартаменты по передаваемому ID")
    @Parameter(name = "id", description = "Идентификатор апартаентов", example = "1")
    public RentApartmentDto findApartment(@RequestParam Long id) {
        return rentService.findApartment(id);
    }


    @PostMapping(BY_LOCATION)
    @Operation(summary = "Получаем местоположение апартаментов")
    @Parameter(name = "id", description = "Идентификатор апартаентов", example = "1")
    public List<RentApartmentDto> findAllApartment(@RequestBody LocationInfoDto locationInfoDto) {
        return rentService.getInfoByLocation(locationInfoDto);
    }

    @PostMapping(BY_WEATHER)
    @Operation(summary = "Получаем информацию по погоде на момент регистрации апартаментов")
    @Parameter(name = "id", description = "Идентификатор апартаентов", example = "1")
    public String findWeatherInformation(@RequestBody WeatherInfoDto weatherInfoDto) throws JsonProcessingException {
        return rentService.getInfoByWeather(weatherInfoDto);
    }

    @PostMapping(APART_BOOKING)
    public String bookingApartment(@RequestBody(required = false) BookingRequestDto bookingRequestDto,
                                   @RequestHeader(required = false) String token) {

        return rentService.booking(bookingRequestDto, authService.tokenValid(token));
    }

    @PostMapping("/{apartmentId}/uploadPhoto")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long apartmentId,
                                              @RequestParam("file") MultipartFile file) {
        try {
            photoService.savePhoto(apartmentId,file);
            return ResponseEntity.ok("Фото загружены");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка загрузки фотографий");
        }

    }

    @GetMapping("/test")
    public String test() {
        return new String(LocalDateTime.now().toString().getBytes());
    }

    @PostMapping(APART_BOOKING + "/")
    public String bookingApartment1(@RequestHeader String token,
                                    @RequestBody BookingRequestDto bookingRequestDto) {

        return null;
    }

    @GetMapping("/print_report")
    public String report(){
        return rentService.printReport();
    }

}
