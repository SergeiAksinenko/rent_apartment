package com.spring.rent.apartment.rent_apartment.dto.geocoder_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class GeocoderResponse {

    @JsonProperty(value = "results")
    private List<ResultsObject> resultsList;
}
