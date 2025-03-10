package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity,Long> {

    List<RatingEntity> findRatingEntitiesByApartment(ApartmentEntity apartment);

    @Query(value = "SELECT a FROM RatingEntity a WHERE a.apartment = :apartment")
    List<RatingEntity> findRatingEntitiesByApartmentJpql(ApartmentEntity apartment);
}
