package com.spring.rent.apartment.rent_apartment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
//@Builder
//@ToString
//@EqualsAndHashCode
@Data
public class UserRegistrationInfoDto {

    @NotBlank(message = "Поле обязательно для заполнения")
    private String nickName;
    private String login;
    private String password;
    private String email;
    private Boolean isStudent;
    private Boolean isCorporateClient;
    private Boolean hasReferral;
    private Boolean isNewUser;
    private Boolean isHolidayPeriod;
    private Integer stayDurationDay;
    private Boolean isSeniorClient;
    private Boolean isOffSession;
    private Boolean isEarlyBooking;
    private Boolean isLocalResident;
    private Boolean hasChildren;
    private Boolean isNonResident;
    private Boolean isBirthday;
    private Boolean hasAdventurePackage;
    private Boolean isVIP;
    private Boolean isLastMinuteBooking;


}
