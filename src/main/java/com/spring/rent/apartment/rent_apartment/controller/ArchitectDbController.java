package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.ArchitectDbDto;
import com.spring.rent.apartment.rent_apartment.service.ArchitectDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/architect")
@RestController
@RequiredArgsConstructor
public class ArchitectDbController {

    private final ArchitectDbService architectDbService;

    @PostMapping("/table_function")
    public String getArchitectDatabase(@RequestBody ArchitectDbDto architectDbDto){
       return architectDbService.getArchitectDatabase(architectDbDto);
    }
}
