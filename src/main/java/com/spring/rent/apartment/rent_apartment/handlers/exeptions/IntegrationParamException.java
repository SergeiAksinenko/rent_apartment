package com.spring.rent.apartment.rent_apartment.handlers.exeptions;

public class IntegrationParamException extends RuntimeException {

    private int exceptionCode;

    public IntegrationParamException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public int getExceptionCode() {
        return exceptionCode;
    }
}
