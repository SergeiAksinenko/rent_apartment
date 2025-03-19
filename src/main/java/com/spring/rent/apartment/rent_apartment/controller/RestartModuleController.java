//package com.spring.rent.apartment.rent_apartment.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RestartModuleController {
//
//    private Logger log = LoggerFactory.getLogger(RestartModuleController.class);
//
//    @PostMapping("/api_apart/upServer")
//    public void upServer(@RequestParam String message) {
//        log.info("Получено сообщение " + message);
//
//        try {
//            log.info("Запуск модуля architect_db");
//
//        } catch (Exception e) {
//            log.error("Ошибка при запуске модуля architect_db", e);
//        }
//    }


//    @PostMapping("/api_apart/upServer/v1")
//    public String addCommentToApartment(@RequestBody RatingDto ratingDto,
//                                        @RequestHeader String token) {
//        return ratingDto.toString();
//    }

//}
