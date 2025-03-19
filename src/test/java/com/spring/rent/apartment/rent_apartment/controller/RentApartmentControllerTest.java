package com.spring.rent.apartment.rent_apartment.controller;


import com.spring.rent.apartment.rent_apartment.dto.RentApartmentDto;
import com.spring.rent.apartment.rent_apartment.service.IntegrationService;
import com.spring.rent.apartment.rent_apartment.service.RentApService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.BY_LOCATION;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.FIND_APARTMENT;
import static com.spring.rent.apartment.rent_apartment.controller.TestUtilConst.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class RentApartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IntegrationService integrationService;

    @MockBean
    private RentApService rentApService;


    @Test
    public void findAllApartmentByLocationTest() throws Exception {

        Mockito.when(integrationService.integrationWithGeocoder(locationInfoDtoForTest())).thenReturn(CITY_LOCATION);

        mockMvc.perform(MockMvcRequestBuilders.post(BY_LOCATION)
                        .content(asJSONstring(locationInfoDtoForTest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray()) // Проверяем, что ответ является массивом
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty()); // Проверяем, что массив не пустой;
    }

    @Test
    public void findAllApartmentLocation_negativeTest() throws Exception {
        Mockito.when(integrationService.integrationWithGeocoder(locationInfoDtoForTest())).thenReturn(CITY_LOCATION);

        mockMvc.perform(MockMvcRequestBuilders.post(BY_LOCATION)
                        .content(asJSONstring(locationInfoDtoForTest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty()); // пустой массив

    }

    @Test
    public void findAllApartment_positiveTest() throws Exception {

        Mockito.when(rentApService.findApartment(1L)).thenReturn(rentApartmentDtoForTest());

        // positive
        mockMvc.perform(MockMvcRequestBuilders.post(FIND_APARTMENT)
                        .param("id", "1")
                        .content(asJSONstring(rentApartmentDtoForTest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfRooms").value(4))
                .andExpect(MockMvcResultMatchers.jsonPath("$.area").value(40))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pricePerNight").value(3500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalGuest").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("описание квартиры"));
    }
}
