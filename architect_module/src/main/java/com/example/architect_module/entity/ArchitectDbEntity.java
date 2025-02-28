package com.example.architect_module.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

}