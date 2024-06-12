package com.spring.rent.apartment.rent_apartment.handlers.exeptions;

public class ApartmentNotFoundException extends RuntimeException{

    private int exceptionCode;

    public ApartmentNotFoundException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
