package com.example.architect_module.service;

import com.example.architect_module.dto.ArchitectDbDto;



public interface ArchitectDbService {

    public String getArchitectDatabase(ArchitectDbDto architectDbDto);

    public String deleteTable(String tableName,String modification);
}
