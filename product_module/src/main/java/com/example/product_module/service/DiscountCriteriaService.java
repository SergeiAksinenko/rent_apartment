package com.example.product_module.service;

import com.example.product_module.dto.UserRegistrationDto;
import com.example.product_module.entity.ProductEntity;

import java.math.BigInteger;

public interface DiscountCriteriaService {

//    BigInteger calculateDiscountCriteria(UserRegistrationDto userRegistrationDto, ProductEntity productEntity);

    boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto);

    BigInteger getDiscountPercentage(ProductEntity productEntity);

}

