package com.spring.rent.apartment.rent_apartment.controller;

import com.spring.rent.apartment.rent_apartment.dto.ArchitectDbDto;
import com.spring.rent.apartment.rent_apartment.service.ArchitectDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.*;

@RestController
@RequiredArgsConstructor
public class ArchitectDbController {

    private final ArchitectDbService architectDbService;

    @PostMapping(TABLE_FUNCTION)
    public String getArchitectDatabase(@RequestBody ArchitectDbDto architectDbDto) {
        return architectDbService.getArchitectDatabase(architectDbDto);
    }
}
