package com.example.architect.controller;

import com.example.architect.dto.ArchitectDbDto;
import com.example.architect.service.ArchitectDbService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;




@RestController
@RequiredArgsConstructor
@Tag(name = "architect_module",description = "API для управления модулем 'architect_module'")
public class ArchitectDbController {

    private final ArchitectDbService architectDbService;

    @PostMapping("/table_function")
    @Operation(summary = "Саздание таблицы")
    @Parameter(name = "modificationType",description = "модификатор создания или изменения таблицы", example = "create,update")
    public String getArchitectDatabase(@RequestBody ArchitectDbDto architectDbDto) {
        return architectDbService.getArchitectDatabase(architectDbDto);

    }
    @GetMapping("/delete_table")
    @Operation(summary = "Удаление таблицы")
    @Parameter(name = "tableName,function",description = "модификатор для удаления таблицы", example = "comment,delete")
    public String deleteTable(@RequestParam String tableName,
                              @RequestParam String function){
        return architectDbService.deleteTable(tableName,function);
    }
}
