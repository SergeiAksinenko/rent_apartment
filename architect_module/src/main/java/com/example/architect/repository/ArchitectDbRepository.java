package com.example.architect.repository;


import com.example.architect.entity.ArchitectDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectDbRepository extends JpaRepository<ArchitectDbEntity,Long> {

}
