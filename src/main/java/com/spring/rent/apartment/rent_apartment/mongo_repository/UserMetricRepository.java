package com.spring.rent.apartment.rent_apartment.mongo_repository;

import com.spring.rent.apartment.rent_apartment.mongo_model.UserMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMetricRepository extends MongoRepository<UserMetric, String> {
}
