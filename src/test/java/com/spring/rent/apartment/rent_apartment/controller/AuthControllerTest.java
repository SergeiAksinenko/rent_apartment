package com.spring.rent.apartment.rent_apartment.controller;


import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.ValidationFieldService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.spring.rent.apartment.rent_apartment.service.impl.AuthServiceImpl.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private ValidationFieldService validationService;


    @Test
    public void userRegistration_positiveTest() throws Exception {

        UserRegistrationInfoDto registrationInfoDto = TestUtilConst.userRegistrationInfoDtoForTest();

        prepareMockitoMock(registrationInfoDto.getNickName());

        prepareMockitoMock(registrationInfoDto.getEmail());

        mockToSave();

        String result = authService.getRegistration(registrationInfoDto);
        assertEquals(SUCCESSFUL_REGISTRATION, result);
    }

    private OngoingStubbing<Optional<UserApplicationEntity>> prepareMockitoMock(String value) {
        return Mockito.when(userRepository.getUserApplicationEntityByEmailJpql(value))
                .thenReturn(Optional.empty());
    }

    private OngoingStubbing<UserApplicationEntity> mockToSave() {
        return Mockito.when(userRepository.save(Mockito.any(UserApplicationEntity.class)))
                .thenReturn(Mockito.any(UserApplicationEntity.class));
    }

    @Test
    public void userRegistration_negativeTest() throws Exception {
        UserRegistrationInfoDto registrationInfoDto = TestUtilConst.userRegistrationInfoDtoForTest();

        UserApplicationEntity existingUser = TestUtilConst.userApplicationEntityForTest();

        Mockito.when(userRepository.getUserApplicationEntityByNickNameJpql(registrationInfoDto
                        .getNickName()))
                .thenReturn(Optional.of(existingUser));

        String result = authService.getRegistration(registrationInfoDto);

        assertEquals(USER_EXISTS, result);
    }


    @Test
    public void userInvalidPasswordTest() throws Exception {
        UserRegistrationInfoDto registrationDto = TestUtilConst.userRegistrationInfoDtoForTest();


        Mockito.when(userRepository.getUserApplicationEntityByNickNameJpql(registrationDto
                        .getNickName()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.getUserApplicationEntityByEmailJpql(registrationDto
                        .getEmail()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.getUserApplicationEntityByLoginJpql(registrationDto
                        .getLogin()))
                .thenReturn(Optional.empty());
        Mockito.when(validationService
                        .isValidPassword(registrationDto
                                .getPassword()))
                .thenReturn(false);

        String result = authService.getRegistration(registrationDto);

        assertEquals(PASSWORD_REQUIREMENT, result);
    }
}
