package com.spring.rent.apartment.rent_apartment.service.impl;

import com.spring.rent.apartment.rent_apartment.dto.UserAuthInfoDto;
import com.spring.rent.apartment.rent_apartment.dto.UserRegistrationInfoDto;
import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import com.spring.rent.apartment.rent_apartment.handlers.exeptions.UserNotFoundException;
import com.spring.rent.apartment.rent_apartment.mapper.RentApartmentMapper;
import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import com.spring.rent.apartment.rent_apartment.service.AuthService;
import com.spring.rent.apartment.rent_apartment.service.ValidationFieldService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.AUTH_NOT_FOUND_USER;
import static com.spring.rent.apartment.rent_apartment.app_constant.AppConstant.NOT_VALID_PASSWORD;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ValidationFieldService validFieldService;

    public final RestTemplate restTemplate = new RestTemplate();

    private final EntityManager entityManager;

    public static final String USER_NOT_FOUND = "Пользователь не найден";

    public static final String SUCCESSFUL_LOGGED_OUT = "Вы успешно вышли из программы";

    public static final String SUCCESSFUL_REGISTRATION = "Успешная регистрация";

    public static final String USER_EXISTS = "Такой пользователь существует в системе";

    public static final String EMAIL_EXISTS = "Такой email существует в системе";

    public static final String USER_LOGIN_EXISTS = "пользователь с таким логином существует";

    public static final String PASSWORD_REQUIREMENT = "пароль должен содержать не менее 8-ми символов";

    public static final String LOGIN_REQUIREMENT = "логин должен содержать символ '@'";

    public static final String LOG_IN_SYSTEM = "Войдите в систему";

    @Value("${product-module.url}")
    private String productModuleUrl;


    @Override
    public String getRegistration(UserRegistrationInfoDto registrationInfoDto) {
        Optional<UserApplicationEntity> userByNickName = userRepository.getUserApplicationEntityByNickNameJpql(registrationInfoDto.getNickName());

        if (userByNickName.isPresent()) {
            return USER_EXISTS;
        }

        Optional<UserApplicationEntity> userByEmail = userRepository.getUserApplicationEntityByEmailJpql(registrationInfoDto.getEmail());
        if (userByEmail.isPresent()) {
            return EMAIL_EXISTS;
        }

        Optional<UserApplicationEntity> userByLogin = userRepository.getUserApplicationEntityByLoginJpql(registrationInfoDto.getLogin());

        if (userByLogin.isPresent()) {
            return USER_LOGIN_EXISTS;
        }
        if (!validFieldService.isValidPassword(registrationInfoDto.getPassword())) {
            return PASSWORD_REQUIREMENT;
        }
        if (!validFieldService.isValidLogin(registrationInfoDto.getLogin())) {
            return LOGIN_REQUIREMENT;
        }
        UserApplicationEntity userApplicationEntity = prepareUser(registrationInfoDto);
        userApplicationEntity.setRegistrationData(LocalDateTime.now());
        userApplicationEntity.setPassword(Base64EncoderDecoder.encode(userApplicationEntity.getPassword()));

        BigDecimal discount = fetchDiscountFromProductModule(registrationInfoDto);
        userApplicationEntity.setDiscount(discount);

        userRepository.save(userApplicationEntity);
        return SUCCESSFUL_REGISTRATION;
    }

    private BigDecimal fetchDiscountFromProductModule(UserRegistrationInfoDto registrationInfoDto) {
        String url = productModuleUrl + "/discount/calculate";
        try {
            ResponseEntity<BigDecimal> response = restTemplate.postForEntity(url,registrationInfoDto, BigDecimal.class);
            return response.getBody() != null ? response.getBody() : BigDecimal.ZERO;
        }catch (Exception e) {
            System.out.println("Ошибка при получении скидки" + e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    public UserApplicationEntity prepareUser(UserRegistrationInfoDto registrationInfoDto) {
        RentApartmentMapper userMapper = Mappers.getMapper(RentApartmentMapper.class);
        return userMapper.registrationDtoToEntity(registrationInfoDto);
    }

    public String getAuthorization(UserAuthInfoDto userAuthInfoDto) {
        UserApplicationEntity user = userRepository.getUserApplicationEntityByLoginJpql(userAuthInfoDto.getLogin())
                .orElseThrow(() -> new UserNotFoundException(AUTH_NOT_FOUND_USER, 602));

        String decodedPassword = Base64EncoderDecoder.decode(user.getPassword());
        user.setPassword(decodedPassword);
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
        UserApplicationEntity user = userRepository.getUserApplicationEntityByLoginJpql(logOutInfoDto.getLogin())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND, 600));

        user.setToken(null);
        userRepository.save(user);
        return SUCCESSFUL_LOGGED_OUT;
    }

    public UserApplicationEntity tokenValid(String token) {
        return findUserByCriteria(token);
    }

    private UserApplicationEntity findUserByCriteria(String token) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder(); // CriteriaRepository
        CriteriaQuery<UserApplicationEntity> query = criteriaBuilder.createQuery(UserApplicationEntity.class);
        Root<UserApplicationEntity> root = query.from(UserApplicationEntity.class); // Корневой элемент для определения сущноности, над которой выполняется запрос
        query.select(root)
                .where(criteriaBuilder.equal(root.get("token"), token));
        try {
            UserApplicationEntity singleResult = entityManager.createQuery(query).getSingleResult();
            return singleResult;
        } catch (NoResultException e) {
            throw new UserNotFoundException(LOG_IN_SYSTEM, 601);
        }
    }


    public String getUsernameFromToken(String token) {
        Optional<UserApplicationEntity> userOptional = userRepository.getUserApplicationEntityByLoginJpql(token); //изменение

        UserApplicationEntity user = userOptional.orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND, 600));

        userRepository.save(user);
        return null;
    }
}
