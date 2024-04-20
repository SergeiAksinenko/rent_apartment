package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "architect_db_info")
@NoArgsConstructor
public class ArchitectDbEntity {

    @Id
    @SequenceGenerator(name="architect_db_infoSequence", sequenceName="architect_db_info_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="architect_db_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "modification_type")
    private String modificationType;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    public ArchitectDbEntity(String tableName, String modificationType) {
        this.tableName = tableName;
        this.modificationType = modificationType;
        this.registrationDate = LocalDateTime.now();
    }
}