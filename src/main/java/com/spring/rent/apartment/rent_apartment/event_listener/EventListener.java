package com.spring.rent.apartment.rent_apartment.event_listener;

import com.spring.rent.apartment.rent_apartment.entity.ApartmentEntity;
import com.spring.rent.apartment.rent_apartment.entity.StatisticInfoEntity;
import com.spring.rent.apartment.rent_apartment.mongo_model.UserMetric;
import com.spring.rent.apartment.rent_apartment.repository.ApartmentRepository;
import com.spring.rent.apartment.rent_apartment.repository.StatisticInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private  final ApartmentRepository apartmentRepository;

    private final StatisticInfoRepository statisticInfoRepository;

    private final MongoTemplate mongoTemplate;

    private static final String POINTCUT_EXPRESSION = "execution(* com.spring.rent.apartment.rent_apartment.service.impl.RentApServiceImpl.findApartment(..))";

    private static final String FIND_APARTMENT  = "findApartment";

    @AfterReturning(pointcut = POINTCUT_EXPRESSION,returning = "result")
    public void testAspectCatchResult(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        log.info(name);
            if(name.equals(FIND_APARTMENT)){
                log.info(result.toString());

                Long apartmentId = (Long) joinPoint.getArgs()[0];
                ApartmentEntity apartment = apartmentRepository.findById(apartmentId).orElse(null);

                if(apartment != null) {
                    StatisticInfoEntity statisticInfoEntity = new StatisticInfoEntity();
                    statisticInfoEntity.setApartment(apartment);
                    statisticInfoEntity.setViewsCount(1L);
                    statisticInfoRepository.save(statisticInfoEntity);
                }
            }
    }

}
