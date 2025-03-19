package com.example.product_module.controller;

import com.example.product_module.dto.UserRegistrationDto;
import com.example.product_module.entity.ProductEntity;
import com.example.product_module.repository.ProductRepository;
import com.example.product_module.service.DiscountCriteriaService;
import com.example.product_module.service.impl.DiscountCriteriaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/discount")
public class DiscountController {

    private final DiscountCriteriaService discountCriteriaService;

    private final DiscountCriteriaServiceImpl discountCriteriaServiceImpl;

    private final ProductRepository productRepository;

    @PostMapping("/calculate")
    public BigDecimal calculateDiscount(@RequestBody UserRegistrationDto userRegistrationDto,
                                        @RequestParam Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(()-> new IllegalArgumentException("Скидка не найдена!"));

        BigInteger totalDiscount = discountCriteriaServiceImpl.calculateDiscountCriteria(userRegistrationDto,product);
        return new BigDecimal(totalDiscount);

    }
}
