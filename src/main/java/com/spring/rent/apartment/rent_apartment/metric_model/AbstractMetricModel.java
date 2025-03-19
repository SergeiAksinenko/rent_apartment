package com.spring.rent.apartment.rent_apartment.metric_model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class AbstractMetricModel {

    private String actionType;

    private Map<LocalDateTime, String> action;
}
