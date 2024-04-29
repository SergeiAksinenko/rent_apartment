package com.spring.rent.apartment.rent_apartment.repository;

import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApplicationEntity, Long> {

    @Query(value = "SELECT a FROM UserApplicationEntity a WHERE a.nickName = :nickName")
    public UserApplicationEntity getUserApplicationEntityByNickNameJpql(String nickName);

    @Query(value = "SELECT a FROM UserApplicationEntity a WHERE a.login = :login")
    public UserApplicationEntity getUserApplicationEntityByLoginJpql(String login);

    //  @Query(nativeQuery = true,value = "SELECT * FROM user_info WHERE token = :token") нативный запрос
    @Query(value = "SELECT a FROM UserApplicationEntity a WHERE a.token = :token")
    public UserApplicationEntity getUserApplicationEntityByTokenNative(String token);

}
