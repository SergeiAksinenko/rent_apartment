package com.example.architect.error_code;

import lombok.Getter;

@Getter
public enum ArchitectErrorCode {

    ERROR_900("неизвестный тип модификатора");

    private String message;

    ArchitectErrorCode(String message) {
        this.message = message;
    }
}
