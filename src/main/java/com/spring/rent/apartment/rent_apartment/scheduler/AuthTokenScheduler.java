package com.spring.rent.apartment.rent_apartment.scheduler;

import com.spring.rent.apartment.rent_apartment.entity.UserApplicationEntity;
import com.spring.rent.apartment.rent_apartment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenScheduler {

    private final UserRepository userRepository;

    @Scheduled(fixedRate = 60000)
    public void checkTokenSession(){

        log.info("Планировщик начинает работу по актуализации токенов");

        List<UserApplicationEntity> tokenList = userRepository.getUserApplicationEntitiesByTokenIsNotNull();

        for(UserApplicationEntity user: tokenList){
            LocalDateTime parsTokenValid = getParsTokenValid(user.getToken());
            if(comparisonDate(parsTokenValid)){
                user.setToken(null);
                userRepository.save(user);
                log.info("Токен " + user.getNickName() +  " не является валидным и был удален.");
            }
        }
        log.info("Планировщик завершил работу по актуализации токенов");
    }

    private LocalDateTime getParsTokenValid(String token){
        int i = token.indexOf("|") + 1;   // распарсивание токена по "|"
        String dateFormat = token.substring(i);
        LocalDateTime dateLimitToken = LocalDateTime.parse(dateFormat);
        return dateLimitToken;
    }

    private boolean comparisonDate(LocalDateTime tokenDate){
       return tokenDate.isBefore(LocalDateTime.now());
    }
}
