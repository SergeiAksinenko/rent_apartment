package com.spring.rent.apartment.rent_apartment.scheduler;

import com.spring.rent.apartment.rent_apartment.entity.AddressEntity;
import com.spring.rent.apartment.rent_apartment.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Slf4j
public class AddressInfoScheduler {

    private final AddressRepository addressRepository;

//    @Scheduled(cron = "0 0 8-9 * * *", zone = "Asia/Yekaterinburg")
    @Scheduled(fixedRate = 35000)
    public void checkAddressInfoScheduler() {

        log.info("Планировщик начал работу по проверке корректности адресной информации");

        List<AddressEntity> addressList = addressRepository.findAll();

        for (AddressEntity address : addressList) {
            boolean isValidAddress = validateAddress(address);
            if (isValidAddress) {
                addressRepository.save(address);
                log.info(" Адрес апартаментов " + " город: " + address.getCity() + " " + " улица: " + address.getStreet() + " не корректный, проверьте информацию и добавьте апартаменты корректно");
            }
            log.info("Планировщик завершил работу по проверке корректности адресной информации");
        }
    }

    private boolean validateAddress(AddressEntity address) {
        String city = address.getCity();
        String street = address.getStreet();

        if (street == null || city == null) {
            return false;
        }
        if (city.length() == 2 || street.length() == 2) {
            return false;
        }
        return true;
    }
}