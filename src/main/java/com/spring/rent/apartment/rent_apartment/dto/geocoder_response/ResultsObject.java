package com.spring.rent.apartment.rent_apartment.dto.geocoder_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResultsObject {

    @JsonProperty(value = "components")
    private Components componentsResult;
}
