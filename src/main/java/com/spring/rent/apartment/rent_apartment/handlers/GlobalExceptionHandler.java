package com.spring.rent.apartment.rent_apartment.handlers;


import com.spring.rent.apartment.rent_apartment.handlers.exeptions.ApartmentDbException;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.ApartmentNotFoundException;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> catchException(UserNotFoundException exception) {
        return ResponseEntity.status(exception.getExceptionCode()).body(exception.getMessage());
    }

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<?> catchException(ApartmentNotFoundException exception) {
        return ResponseEntity.status(exception.getExceptionCode()).body(exception.getMessage());
    }

    @ExceptionHandler(ApartmentDbException.class)
    public ResponseEntity<?> catchException(ApartmentDbException exception) {
        return ResponseEntity.status(exception.getExceptionCode()).body(exception.getMessage());
    }
}