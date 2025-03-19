package com.example.product_module.controller;

import com.example.product_module.product_kafka_consumer_config.MyTopicConsumerListener;
import com.example.product_module.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final MyTopicConsumerListener myTopicConsumerListener;

    @GetMapping("/report")
    public void printReport(){
    productService.printReport();
    }

    @GetMapping("/prepare_discount")  // вопрос
    public String prepareDiscount(String bookingValue){
        return null;
    }

    @GetMapping("/messages")
    public List<String> messages(){
        List<String> messages = myTopicConsumerListener.getMessages();
        return messages;
    }
}
