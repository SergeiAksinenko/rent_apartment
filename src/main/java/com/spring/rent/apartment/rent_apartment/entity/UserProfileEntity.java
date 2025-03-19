package com.spring.rent.apartment.rent_apartment.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @SequenceGenerator(name = "user_profileSequence", sequenceName = "user_profile_sequence", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_profileSequence")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApplicationEntity user;

    @Column(name = "is_new_user")
    private boolean isNewUser;

    @Column(name = "is_student")
    private boolean isStudent;

    @Column(name = "is_corporate_client")
    private boolean isCorporateClient;

    @Column(name = "is_senior")
    private boolean isSenior;

    @Column(name = "has_referral_link")
    private boolean hasReferralLink;

    @Column(name = "stay_duration_days")
    private int stayDurationDays;

    @Column(name = "is_local_resident")
    private boolean isLocalResident;

    @Column(name = "has_children")
    private boolean hasChildren;

    @Column(name = "is_vip_client")
    private boolean isVipClient;

    @Column(name = "booking_in_off_season")
    private boolean bookingInOffSeason;

    @Column(name = "booking_in_off_days")
    private int bookingInOffDays;

    @Column(name = "booking_advance_days")
    private int bookingAdvanceDays;

    @Column(name = "booking_last_minute")
    private boolean bookingLastMinute;

    @Column(name = "is_birthday")
    private boolean isBirthday;

    @Column(name = "adventure_package")
    private boolean adventurePackage;
}
