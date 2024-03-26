package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.RentApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentApartmentRepository extends JpaRepository<RentApartmentEntity,Long> {
}
