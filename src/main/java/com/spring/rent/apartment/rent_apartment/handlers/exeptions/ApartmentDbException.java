package com.spring.rent.apartment.rent_apartment.handlers.exeptions;

public class ApartmentDbException extends RuntimeException {

    private int exceptionCode;

    public ApartmentDbException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}

