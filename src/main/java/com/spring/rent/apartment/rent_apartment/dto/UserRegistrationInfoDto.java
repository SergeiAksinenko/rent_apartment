package com.spring.rent.apartment.rent_apartment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

}
