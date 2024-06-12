package com.spring.rent.apartment.rent_apartment.handlers.exeptions;

public class UserNotFoundException  extends RuntimeException{

    private int exceptionCode;

    public UserNotFoundException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
