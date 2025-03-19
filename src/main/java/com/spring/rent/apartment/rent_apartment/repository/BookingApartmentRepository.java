package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.BookingApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingApartmentRepository extends JpaRepository<BookingApartmentEntity,Long> {
}
