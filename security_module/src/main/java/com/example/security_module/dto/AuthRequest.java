package com.example.security_module.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String password;
}