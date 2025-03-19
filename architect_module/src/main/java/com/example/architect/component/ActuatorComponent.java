package com.example.architect.component;//package com.example.architect_module.component;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//
//
//@Component
//public class ActuatorComponent {
//
//    private Logger log = LoggerFactory.getLogger(ActuatorComponent.class);
//
//    RestTemplate restTemplate = new RestTemplate();
//
//    @WriteOperation
//    public void dropServer() {
//
//
//        log.info("Запущен поток остановки модуля architect_db " + LocalDateTime.now());
//
//        sentInfoToRentModule();
//
////        sentInfoToRentModuleTest();
//
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//                log.info("модуль architect_db остановлен " + LocalDateTime.now());
//                System.exit(0); // Завершение JVM
//            } catch (InterruptedException e) {
//                log.error("Ошибка!");
//                Thread.currentThread().interrupt();
//            }
//        });
//
//        thread.setDaemon(false); // Сделать поток не демоном
//        thread.start();
//    }
//
//    public void sentInfoToRentModule() {
//        String url = "http://localhost:8093/api_apart/upServer?message=%s";
//
//        String message = "Сервер будет отключен через 5 секунд в связи с выполнением миграции" + LocalDateTime.now();
//        restTemplate.exchange(String.format(url, message),
//                HttpMethod.POST,
//                new HttpEntity<>(null),
//                Void.class);
//    }

//    public void sentInfoToRentModuleTest() {
//        String url = "http://localhost:8093/api_apart/upServer/v1";
//
//        RatingDto ratingDto = new RatingDto(1L, "test", 1);
//
//        restTemplate.exchange(url,
//                HttpMethod.POST,
//                new HttpEntity<>(null, null),
//                String.class);
//    }

//    private HttpHeaders prepareHeaders() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", token);
//        public static Map<String, String> createHeadersWithToken () {
//            Map<String, String> headers = new HashMap<>();
//            headers.put("token", "123");
//            return headers;
//        }
//    }
//}