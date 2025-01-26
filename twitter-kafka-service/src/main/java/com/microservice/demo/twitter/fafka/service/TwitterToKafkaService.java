package com.microservice.demo.twitter.fafka.service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TwitterToKafkaService {

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaService.class,args);
    }
}
