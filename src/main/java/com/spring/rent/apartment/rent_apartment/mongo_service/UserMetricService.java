package com.spring.rent.apartment.rent_apartment.mongo_service;

import com.spring.rent.apartment.rent_apartment.mongo_model.UserMetric;
import com.spring.rent.apartment.rent_apartment.mongo_repository.UserMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMetricService {

    private final UserMetricRepository userMetricRepository;

    public UserMetric registerUserMetric(UserMetric userMetric) {
        userMetric.setRegisterDate(LocalDateTime.now());
        return userMetricRepository.save(userMetric);
    }

    public List<UserMetric> getAllUserMetrics(){
        return userMetricRepository.findAll();
    }

    public UserMetric getUserMetricById(String id){
        return userMetricRepository.findById(id).orElse(null);
    }

    public UserMetric updateUserMetric(String id,UserMetric userMetric){
        UserMetric userMetricToUpdate = userMetricRepository.findById(id).orElse(null);
        if(userMetricToUpdate == null){
            userMetricToUpdate.setBookedApartments(userMetric.getBookedApartments());
            userMetricToUpdate.setLastLoginDate(userMetric.getLastLoginDate());
            userMetricToUpdate.setOnlineDuration(userMetric.getOnlineDuration());
            userMetricToUpdate.setLogoutTime(userMetric.getLogoutTime());
            userMetricToUpdate.setAvailableDiscounts(userMetric.getAvailableDiscounts());
        }
        return null;
    }
    public void deleteUserMetric(String id){
        userMetricRepository.deleteById(id);
    }

}
