package com.example.architect_module.controller;

import com.example.architect_module.dto.ArchitectDbDto;
import com.example.architect_module.service.ArchitectDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.TABLE_FUNCTION;


@RestController
@RequiredArgsConstructor
public class ArchitectDbController {

    private final ArchitectDbService architectDbService;

    @PostMapping(TABLE_FUNCTION)
    public String getArchitectDatabase(@RequestBody ArchitectDbDto architectDbDto) {
        return architectDbService.getArchitectDatabase(architectDbDto);
    }
    @GetMapping("/delete_table")
    public String deleteTable(@RequestParam String tableName,
                              @RequestParam String function){
        return architectDbService.deleteTable(tableName,function);
    }
}
