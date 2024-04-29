package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.ValidationFieldService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.AUTH_NOT_FOUND_USER;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.NOT_VALID_PASSWORD;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ValidationFieldService validFieldService;

    public static final String USER_NOT_FOUND = "Пользователь не найден";

    public static final String SUCCESSFUL_LOGGED_OUT = "Вы успешно вышли из программы";

    public static final String SUCCESSFUL_REGISTRATION = "успешная регистрация";

    public static final String USER_EXISTS = "Такой пользователь существует в системе";

    public static final String USER_LOGIN_EXISTS = "пользователь с таким логином существует";

    public static final String PASSWORD_REQUIREMENT = "пароль должен содержать не менее 8-ми символов";

    public static final String LOGIN_REQUIREMENT = "логин должен содержать символ '@'";

    public static final String LOG_IN_SYSTEM = "Войдите в систему";


    @Override
    public String getRegistration(UserRegistrationInfoDto registrationInfoDto) {
        UserApplicationEntity userByNickName = userRepository.getUserApplicationEntityByNickNameJpql(registrationInfoDto.getNickName());

        if (!isNull(userByNickName)) {
            return USER_EXISTS;
        }

        UserApplicationEntity userByLogin = userRepository.getUserApplicationEntityByLoginJpql(registrationInfoDto.getLogin());

        if (!isNull(userByLogin)) {
            return USER_LOGIN_EXISTS;
        }
        if (!validFieldService.isValidPassword(registrationInfoDto.getPassword())) {
            return PASSWORD_REQUIREMENT;
        }
        if (!validFieldService.isValidLogin(registrationInfoDto.getLogin())) {
            return LOGIN_REQUIREMENT;
        }

        userRepository.save(prepareUser(registrationInfoDto));
        return SUCCESSFUL_REGISTRATION;
    }

    public UserApplicationEntity prepareUser(UserRegistrationInfoDto registrationInfoDto) {
        return new UserApplicationEntity(registrationInfoDto.getNickName(),
                registrationInfoDto.getLogin(),
                registrationInfoDto.getPassword());
    }

    public String getAuthorization(UserAuthInfoDto userAuthInfoDto) {
        Optional<UserApplicationEntity> optionalUser = Optional.ofNullable(userRepository.getUserApplicationEntityByLoginJpql(userAuthInfoDto.getLogin()));
        UserApplicationEntity user = optionalUser.orElseThrow(() -> new RuntimeException(AUTH_NOT_FOUND_USER));

        if (!userAuthInfoDto.getPassword().equals(user.getPassword())) {
            return NOT_VALID_PASSWORD;
        }

        String token = tokenGeneration();


        user.setToken(token);

        userRepository.save(user);

        return token;
    }

    public String tokenGeneration() {
        return UUID.randomUUID() + "|" + LocalDateTime.now().plusDays(1L);

    }

    @Override
    public String getLogOut(UserRegistrationInfoDto logOutInfoDto) {
        Optional<UserApplicationEntity> userOptional = Optional.ofNullable(userRepository.getUserApplicationEntityByLoginJpql(logOutInfoDto.getLogin()));
        UserApplicationEntity user = userOptional.orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
        user.setToken(null);
        userRepository.save(user);
        return SUCCESSFUL_LOGGED_OUT;
    }

    public void tokenValid(String token) {
        Optional<UserApplicationEntity> userOptional = Optional.ofNullable(findUserByCriteria(token));
        UserApplicationEntity user = userOptional.orElseThrow(()-> new RuntimeException(LOG_IN_SYSTEM));
        userRepository.save(user);
        }

        private final EntityManager entityManager;
        private UserApplicationEntity findUserByCriteria(String token){
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder(); // CriteriaRepository
            CriteriaQuery<UserApplicationEntity> query = criteriaBuilder.createQuery(UserApplicationEntity.class);
            Root<UserApplicationEntity> root = query.from(UserApplicationEntity.class); // Корневой элемент для определения сущноности, над которой выполняется запрос
            query.select(root)
                    .where(criteriaBuilder.equal(root.get("token"), token));
            UserApplicationEntity result = entityManager.createQuery(query).getSingleResult();

            return result;
        }
    }
