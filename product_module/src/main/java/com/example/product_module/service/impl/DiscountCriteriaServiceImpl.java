package com.example.product_module.service.impl;


import com.example.product_module.dto.UserRegistrationDto;
import com.example.product_module.entity.ProductEntity;
import com.example.product_module.service.DiscountCriteriaService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class DiscountCriteriaServiceImpl  implements DiscountCriteriaService {

    private final List<DiscountCriteriaService> discountCriteriaList;



    public DiscountCriteriaServiceImpl() {
        this.discountCriteriaList = List.of(
                new NewUserDiscountCriteria(),
                new HolidayDiscountCriteria(),
                new LongStartDiscountCriteria(),
                new ReferralDiscountCriteria(),
                new StudentDiscountCriteria(),
                new CorporateDiscountCriteria(),
                new SeniorDiscountCriteria(),
                new OffSeasonDiscountCriteria(),
                new EarlyBookingDiscountCriteria(),
                new LocalResidentDiscountCriteria(),
                new FamilyDiscountCriteria(),
                new NonResidentDiscountCriteria(),
                new BirthdayDiscountCriteria(),
                new AdventureDiscountCriteria(),
                new VIPDiscountCriteria(),
                new LastMinuteDiscountCriteria()
        );
    }

    public BigInteger calculateDiscountCriteria(UserRegistrationDto userRegistrationDto,ProductEntity productEntity) {
        BigInteger totalDiscount = BigInteger.ZERO;

        for (DiscountCriteriaService criteria : discountCriteriaList) {
            if (criteria.isApplicableDiscount(userRegistrationDto)) {
                totalDiscount = totalDiscount.add(criteria.getDiscountPercentage(productEntity));
            }
        }
        return totalDiscount;
    }

    @Override
    public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
        return discountCriteriaList.stream().anyMatch(criteria -> criteria.isApplicableDiscount(userRegistrationDto));
    }

    @Override
    public BigInteger getDiscountPercentage(ProductEntity productEntity) {
        return productEntity.getPercentage();
    }

    private static class NewUserDiscountCriteria implements DiscountCriteriaService {

        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isNewUser();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class HolidayDiscountCriteria implements DiscountCriteriaService {

        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isHolidayPeriod();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class LongStartDiscountCriteria implements DiscountCriteriaService {


        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.getStayDurationDays() > 7;
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class ReferralDiscountCriteria implements DiscountCriteriaService {


        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isHasReferral();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class StudentDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isStudent();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class CorporateDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isCorporateClient();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class SeniorDiscountCriteria implements DiscountCriteriaService {

        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isSeniorClient();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class OffSeasonDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isOffSeason();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }
    private static class EarlyBookingDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isEarlyBooking();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }
    private static class LocalResidentDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isLocalResident();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class FamilyDiscountCriteria implements DiscountCriteriaService {

        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isHasChildren();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class NonResidentDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isNonResident();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class BirthdayDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isBirthday();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class AdventureDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isHasAdventurePackage();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class VIPDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isVIP();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }

    private static class LastMinuteDiscountCriteria implements DiscountCriteriaService {
        @Override
        public boolean isApplicableDiscount(UserRegistrationDto userRegistrationDto) {
            return userRegistrationDto.isLastMinuteBooking();
        }

        @Override
        public BigInteger getDiscountPercentage(ProductEntity productEntity) {
            return productEntity.getPercentage();
        }
    }
}
