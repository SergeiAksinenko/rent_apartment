package com.example.architect.service.impl;


import com.example.architect.dto.ArchitectDbDto;
import com.example.architect.handlers.exceptions.ApartmentDbException;
import com.example.architect.service.ArchitectDbService;
import com.example.architect.service.Base64EncoderDecoder;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArchitectDbServiceImpl implements ArchitectDbService {

    private final JdbcTemplate jdbcTemplate;

    private final Flyway flyway;

    @Value("/Users/a1/Documents/Java_Program/rent_apartment/architect_module/src/main/resources/db/migration")

//    @Value("${migration.script.pass}")
    private String MIGRATION_PATH;


    @Value("V%s__%s_%s.sql")
//    @Value("${script.name.template}")
    private String SCRIPT_NAME_TEMPLATE;

    public static final String NOT_MODIFICATION_TYPE = "неизвестный тип модификатора";

    @Override
    public String getArchitectDatabase(ArchitectDbDto architectDbDto) {
        String tableName = architectDbDto.getTableName();
        String modificationType = architectDbDto.getModificationType();
        Map<String, String> parameters = architectDbDto.getParameters();

        StringBuilder stringBuilder = new StringBuilder();
        if (modificationType.equalsIgnoreCase("CREATE")) {
            stringBuilder.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String columName = entry.getKey();
                String columType = entry.getValue();
                stringBuilder.append(columName).append(" ").append(columType).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(")");

            String output = stringBuilder.toString();
            writeResultToFile(output, prepareScriptName(tableName, modificationType));

            flyway.migrate();

            return output;
        }
        if (modificationType.equalsIgnoreCase("UPDATE")) {
            stringBuilder.append("ALTER TABLE ").append(tableName).append(" ");

            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                String columName = entry.getKey();
                String columType = entry.getValue();
                stringBuilder.append(columName).append(" ").append(columType).append(", ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            String output = stringBuilder.toString();
            writeResultToFile(output, prepareScriptName(tableName, modificationType));

            flyway.migrate();

            return output;
        }

        throw new ApartmentDbException(NOT_MODIFICATION_TYPE,900);
    }

    private void writeResultToFile(String output, String scriptName) {
        File file = new File(Base64EncoderDecoder.decode(MIGRATION_PATH), scriptName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(output);
        } catch (IOException e) {
           throw  new RuntimeException("ошибка сохранения скрипта миграции");
        }
    }

    private String prepareScriptName(String tableName, String modificationType) {
        Integer count = jdbcTemplate.queryForObject("SELECT MAX(CAST(version AS INTEGER)) FROM flyway_schema_history", new SingleColumnRowMapper<>(Integer.class));
        count = (count == null) ? 0 : count + 1;
        System.out.println("Creating migration script with version: " + count);

        return String.format(SCRIPT_NAME_TEMPLATE, count, modificationType, tableName);
    }
    private void updateFlyWaySchemas(String query){
        jdbcTemplate.update(query);
    }

    public String deleteTable(String tableName,String modification){
        String tableScript = prepareScriptName(tableName, modification);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE ").append(tableName);

        String output = stringBuilder.toString();
        writeResultToFile(output, prepareScriptName(tableName, modification));

        flyway.migrate();

        return output;
    }
}



