package com.spring.rent.apartment.rent_apartment.dto.geocoder_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class Components {

    private String city;

    private String town;
}
