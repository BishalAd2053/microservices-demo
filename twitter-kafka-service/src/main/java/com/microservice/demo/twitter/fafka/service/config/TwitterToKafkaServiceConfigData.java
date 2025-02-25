package com.microservice.demo.twitter.fafka.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-kafka-service")
public class TwitterToKafkaServiceConfigData {
    private List<String>twitterKeywords;


}
