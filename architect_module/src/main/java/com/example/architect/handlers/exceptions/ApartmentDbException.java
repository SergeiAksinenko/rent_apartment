package com.example.architect.handlers.exceptions;

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

