package com.example.architect.service;


import com.example.architect.dto.ArchitectDbDto;

public interface ArchitectDbService {

    public String getArchitectDatabase(ArchitectDbDto architectDbDto);

    public String deleteTable(String tableName,String modification);
}
