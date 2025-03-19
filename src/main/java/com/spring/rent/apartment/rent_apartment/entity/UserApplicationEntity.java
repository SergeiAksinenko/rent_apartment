package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_info")
@NoArgsConstructor
public class UserApplicationEntity {

    @Id
    @SequenceGenerator(name="user_infoSequence", sequenceName="user_info_sequence",initialValue = 2, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_infoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "nick_name",unique = true)
    private String nickName;

    @Column(name = "login",unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "registration_data")
    private LocalDateTime registrationData;

    @Column(name = "is_student")
    private Boolean isStudent;

    @Column(name = "is_corporate_client")
    private Boolean isCorporateClient;

    @Column(name = "has_referral")
    private Boolean hasReferral;

    @Column(name = "is_new_user")
    private Boolean isNewUser;

    @Column(name = "is_holiday_period")
    private Boolean isHolidayPeriod;

    @Column(name = "stay_duration_days")
    private Integer stayDurationDay;

    @Column(name = "is_senior_client")
    private Boolean isSeniorClient;

    @Column(name = "is_off_season")
    private Boolean isOffSession;

    @Column(name = "is_early_booking")
    private Boolean isEarlyBooking;

    @Column(name = "is_local_resident")
    private Boolean isLocalResident;

    @Column(name = "has_children")
    private Boolean hasChildren;

    @Column(name = "is_non_resident")
    private Boolean isNonResident;

    @Column(name = "is_birthday")
    private Boolean isBirthday;

    @Column(name = "has_adventure_package")
    private Boolean hasAdventurePackage;

    @Column(name = "is_vip")
    private Boolean isVIP;

    @Column(name = "is_last_minute_booking")
    private Boolean isLastMinuteBooking;

    @Column(name = "token")
    private String token;

    @Column(name = "discount")
    private BigDecimal discount;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserProfileEntity userProfile;

    public UserApplicationEntity(String nickName, String login, String password,String email) {
        this.nickName = nickName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.registrationData = LocalDateTime.now();
    }
}
