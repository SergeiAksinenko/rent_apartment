package com.example.architect.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ArchitectDbDto {

    private String tableName;

    private String modificationType;

    private Map<String,String> parameters;

}