package com.example.architect_module.repository;

import com.example.architect_module.entity.ArchitectDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectDbRepository extends JpaRepository<ArchitectDbEntity,Long> {

}
