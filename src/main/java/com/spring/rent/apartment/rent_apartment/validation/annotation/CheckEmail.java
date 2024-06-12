package com.spring.rent.apartment.rent_apartment.validation.annotation;

import com.spring.rent.apartment.rent_apartment.validation.annotation.impl.CheckEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    String message() default "Адрес электронной почты недействительный";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
