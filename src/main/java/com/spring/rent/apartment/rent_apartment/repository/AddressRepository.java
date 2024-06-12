package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

//    @Query(nativeQuery = true,value = "SELECT * FROM address_info WHERE city = :city AND street = :street") // нативный запрос
    @Query(value = "SELECT a FROM AddressEntity a WHERE a.city = :city AND a.street = :street") // JPQL запрос
    public AddressEntity findByCityAndStreetNative(String city,String street);

    public List<AddressEntity> findAll();


}
