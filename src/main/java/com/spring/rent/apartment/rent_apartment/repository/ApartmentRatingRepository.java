package com.spring.rent.apartment.rent_apartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRatingRepository extends JpaRepository<ApartmentRatingRepository,Long> {
}
