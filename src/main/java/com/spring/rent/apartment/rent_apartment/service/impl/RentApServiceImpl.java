package com.spring.rent.apartment.rent_apartment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.rent.apartment.rent_apartment.dto.*;
import com.spring.rent.apartment.rent_apartment.entity.*;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.ApartmentNotFoundException;
import com.spring.rent.apartment.rent_apartment.mapper.RentApartmentMapper;
import com.spring.rent.apartment.rent_apartment.repository.*;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.APARTMENT_NOT_FOUND;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.SAVE_APARTMENT_SUCCESSFULLY;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RentApServiceImpl implements RentApService {

    public final RentApartmentMapper mapper;

    public final AddressRepository addressRepository;

    public final ApartmentRepository apartmentRepository;

    public final RatingRepository ratingRepository;

    public final IntegrationService integrationService;

    public final AuthService authService;

    private final BookingApartmentRepository bookingApartmentRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;


    public static final String APARTMENT_IS_EXIST = "Апартаменты уже зарегистрированы";

    public static final String COMMENT_IS_DONE = "Спасибо за отзыв!";

    public static final String GEOCODER_EXCEPTION = "Проблема с обработкой геокодера";

    public static final String SUCCESSFUL_BOOKING_NOW = "Успешное бронирование апартаментов, детали бронирования и скидка была отправлена на вашу почту";

    public static final String SUCCESSFUL_BOOKING_24 = "Успешное бронирование апартаментов, детали бронирования и скидка была отправлена на вашу почту в течение 24-х часов ";

    private final UserRepository userRepository;

    public String saveRegApartment(RentApartmentDto rentApartmentDto) {
        AddressEntity existingAddress = addressRepository.findByCityAndStreetNative(rentApartmentDto.getCity(), rentApartmentDto.getStreet());
        if (!isNull(existingAddress)) {
            return APARTMENT_IS_EXIST;
        }
        ApartmentEntity apartment = mapper.dtoToApartmentEntity(rentApartmentDto);

        AddressEntity address = mapper.dtoToAddressEntity(rentApartmentDto);

        apartmentRepository.save(apartment);

        address.setApartment(apartment);

        addressRepository.save(address);

        return SAVE_APARTMENT_SUCCESSFULLY;
    }

    @Override
    public String addCommentToApartment(RatingDto rating) {
        ApartmentEntity apartment = apartmentRepository.findById(rating.getApartmentId())
                .orElseThrow(() -> new ApartmentNotFoundException(APARTMENT_NOT_FOUND, 700));

        RatingEntity ratingEntity = mapper.DtoToRatingEntity(rating,
                apartment,
                LocalDateTime.now());

        ratingRepository.save(ratingEntity);

        if (ratingEntity.getApartment() != null) {
            calculateGlobalRating(ratingEntity.getApartment());
        }
        return COMMENT_IS_DONE;
    }

    private void calculateGlobalRating(ApartmentEntity apartment) {
        List<RatingEntity> rating = ratingRepository.findRatingEntitiesByApartmentJpql(apartment);
        int ratingSum = 0;
        for (RatingEntity ratings : rating) {
            ratingSum += ratings.getRating();
        }
        double globalRating = (double) ratingSum / rating.size();
        apartment.setGlobalRating(globalRating);

        apartmentRepository.save(apartment);
    }

    public RentApartmentDto findApartment(Long id) {
        ApartmentEntity apartment = apartmentRepository.findById(id).orElseThrow(() -> new ApartmentNotFoundException(APARTMENT_NOT_FOUND, 700));
        return mapper.entityToApartmentDto(apartment, apartment.getAddress());
    }

    @Override
    public List<RentApartmentDto> getInfoByLocation(LocationInfoDto locationInfoDto) {
        String city = integrationService.integrationWithGeocoder(locationInfoDto);
        List<AddressEntity> byCity = addressRepository.findByCity(city);
        return byCity.stream().map(e -> mapper.entityToApartmentDto(e.getApartment(), e)).toList();
    }

    @Override
    public String getInfoByWeather(WeatherInfoDto weatherInfoDto) throws JsonProcessingException {
        return integrationService.integrationWeatherCode(weatherInfoDto);
    }


//    private String parsGeocoderTimezoneResult(String value){
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JsonNode jsonNode = objectMapper.readTree(value);
//            return jsonNode.get("results").get(0).get("timezone").get("name").textValue();
//        } catch(JsonProcessingException e) {
//            throw new RuntimeException(GEOCODER_EXCEPTION);
//        }
//    }

    public String booking(BookingRequestDto bookingRequestDto,UserApplicationEntity user) {
        // cчетчик availability просто на true и false, переделать на даты

        ApartmentEntity apartment = apartmentRepository.findById(bookingRequestDto.getId())
                .orElseThrow(() -> new ApartmentNotFoundException(APARTMENT_NOT_FOUND, 600));

        if (!apartment.getAvailability().equals("true")) {
            throw new RuntimeException(APARTMENT_NOT_FOUND);
        }

        BookingApartmentEntity bookingApartmentEntity = mapper.dtoToBookingApartmentEntity(bookingRequestDto);

        bookingApartmentEntity = bookingApartmentRepository.save(bookingApartmentEntity);

        try {
            integrationService.integrationProductModule(bookingApartmentEntity.toString());
            kafkaTemplate.send("my_topic", bookingApartmentEntity.toString());

            return SUCCESSFUL_BOOKING_NOW;
        } catch (ResourceAccessException e) {
            kafkaTemplate.send("my_topic", bookingApartmentEntity.toString());
            return SUCCESSFUL_BOOKING_24;
        }
    }

    @Override
    public String printReport() {
        List<BookingApartmentEntity> all = bookingApartmentRepository.findAll();

        File file = new File("/Users/a1/Documents/Java_Program/rent_apartment/report.xlsx");
        try(FileInputStream fileInputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fileInputStream)) {

          Sheet sheet = workbook.getSheetAt(0);
          int rowNumber = 1;
          for(BookingApartmentEntity info : all){
              Row row = sheet.createRow(rowNumber++);
              row.createCell(0).setCellValue(prepareFullAddress(info));
              row.createCell(1).setCellValue(info.getBookingTime().toString());
              row.createCell(2).setCellValue(info.getStartBooking().toString());
              row.createCell(3).setCellValue(info.getEndBooking().toString());
              row.createCell(4).setCellValue(info.getTotalSumBooking());
              row.createCell(5).setCellValue(info.getUser().getNickName());
          }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException("В ходе выгрузки отчета произошла ошибка");
        }
        return "Отчет загружен";
    }

    private String prepareFullAddress(BookingApartmentEntity info) {
        return info.getApartment().getAddress().getCity()  + " " + info.getApartment().getAddress().getStreet();
    }
}

