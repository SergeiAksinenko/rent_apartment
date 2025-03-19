package com.example.architect.handlers;



import com.example.architect.handlers.exceptions.ApartmentDbException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ApartmentDbException.class)
    public ResponseEntity<?> catchException(ApartmentDbException exception) {
        return ResponseEntity.status(exception.getExceptionCode()).body(exception.getMessage());
    }
}