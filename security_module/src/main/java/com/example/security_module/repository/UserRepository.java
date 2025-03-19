package com.example.security_module.repository;

import com.example.security_module.entity.UserApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApplicationEntity, Long> {

    Optional<UserApplicationEntity> findByNickName(String username);
}
