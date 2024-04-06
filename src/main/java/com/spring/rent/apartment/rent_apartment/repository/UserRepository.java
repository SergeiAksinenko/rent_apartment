package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApplicationEntity, Long> {

    public UserApplicationEntity getUserApplicationEntityByNickName(String nickName);

    public UserApplicationEntity getUserApplicationEntityByLogin(String login);

    public UserApplicationEntity getUserApplicationEntityByToken(String token);

}
