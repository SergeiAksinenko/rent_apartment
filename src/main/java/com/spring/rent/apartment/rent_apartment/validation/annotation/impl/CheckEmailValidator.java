package com.spring.rent.apartment.rent_apartment.validation.annotation.impl;

import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import com.spring.rent.apartment.rent_apartment.validation.annotation.CheckEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    private final UserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
