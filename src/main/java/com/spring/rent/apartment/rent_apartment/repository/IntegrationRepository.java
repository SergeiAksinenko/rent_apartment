package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.IntegrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationEntity,String> {
}
