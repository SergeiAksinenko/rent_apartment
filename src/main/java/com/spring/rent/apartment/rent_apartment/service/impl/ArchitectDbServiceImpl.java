package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.ArchitectDbDto;
import com.spring.rent.apartment.rent_apartment.repository.ArchitectDbRepository;
import com.spring.rent.apartment.rent_apartment.service.ArchitectDbService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

@Service
public class ArchitectDbServiceImpl implements ArchitectDbService {


    @Override
    public String getArchitectDatabase(ArchitectDbDto architectDbDto) {
        String tableName = architectDbDto.getTableName();
        String modificationType = architectDbDto.getModificationType();
        Map<String, String> parameters = architectDbDto.getParameters();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE ").append(tableName).append(" (");

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            String columName = entry.getKey();
            String columType = entry.getValue();
            stringBuilder.append(columName).append(" ").append(columType).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");

        String output = stringBuilder.toString();
        writeResultToFile(output);
        return output;

    }
    private void writeResultToFile(String output){
        try (FileOutputStream outputStream = new FileOutputStream("CreateTable.txt")){
            byte[] bytes = output.getBytes();
            outputStream.write(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

