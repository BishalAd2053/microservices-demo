package com.microservice.demo.twitter.fafka.service;

import com.microservice.demo.twitter.fafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservice.demo.twitter.fafka.service.runner.impl.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final StreamRunner streamRunner;
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    public TwitterToKafkaServiceApplication(StreamRunner streamRunner, TwitterToKafkaServiceConfigData configData) {
        this.streamRunner = streamRunner;
        this.twitterToKafkaServiceConfigData = configData;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     LOG.info("Application started Bro");
     LOG.info(Arrays.toString(twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[]{})));
     streamRunner.start();
    }
}
