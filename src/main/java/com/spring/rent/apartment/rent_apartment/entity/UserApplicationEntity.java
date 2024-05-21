package com.spring.rent.apartment.rent_apartment.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "registration_data")
    private LocalDateTime registrationData;

    @Column(name = "token")
    private String token;

    public UserApplicationEntity(String nickName, String login, String password) {
        this.nickName = nickName;
        this.login = login;
        this.password = password;
        this.registrationData = LocalDateTime.now();
    }
}
