package com.example.product_module.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {

        private boolean isNewUser;
        private boolean hasReferral;
        private int stayDurationDays;
        private boolean isStudent;
        private boolean isCorporateClient;
        private boolean isSeniorClient;
        private boolean isLocalResident;
        private boolean isHolidayPeriod;
        private boolean isBirthday;
        private boolean isLastMinuteBooking;
        private boolean isOffSeason;
        private boolean isEarlyBooking;
        private boolean isHasChildren;
        private boolean isNonResident;
        private boolean hasAdventurePackage;
        private boolean isVIP;
}
