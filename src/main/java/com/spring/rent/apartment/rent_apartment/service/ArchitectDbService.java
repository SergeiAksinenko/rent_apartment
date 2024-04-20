package com.spring.rent.apartment.rent_apartment.service;

import com.spring.rent.apartment.rent_apartment.dto.ArchitectDbDto;
import org.springframework.stereotype.Service;


public interface ArchitectDbService {

    public String getArchitectDatabase(ArchitectDbDto architectDbDto);
}
