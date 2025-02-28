package com.spring.rent.apartment.rent_apartment.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {

    private String info;
    private boolean success;
}
