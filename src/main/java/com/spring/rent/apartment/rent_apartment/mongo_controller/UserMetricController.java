package com.spring.rent.apartment.rent_apartment.mongo_controller;


import com.spring.rent.apartment.rent_apartment.mongo_model.UserMetric;
import com.spring.rent.apartment.rent_apartment.mongo_service.UserMetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserMetricController {

    private final UserMetricService userMetricService;

    @PostMapping("/register_user_metric")
    public UserMetric registerUserMetric(@RequestBody UserMetric userMetric) {
        return userMetricService.registerUserMetric(userMetric);
    }

    @GetMapping
    public List<UserMetric> getAllUserMetrics() {
        return userMetricService.getAllUserMetrics();
    }

    @GetMapping("/{id}")
    public UserMetric getUserMetricById(@PathVariable String id) {
        return userMetricService.getUserMetricById(id);
    }

    @PutMapping("/{id}")
    public UserMetric updateUserMetric(@PathVariable String id, @RequestBody UserMetric userMetric) {
        return userMetricService.updateUserMetric(id, userMetric);
    }
    @DeleteMapping("/{id}")
    public void deleteUserMetric(@PathVariable String id) {
        userMetricService.deleteUserMetric(id);
    }
}
