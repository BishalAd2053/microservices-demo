package com.microservice.demo.twitter.fafka.service.runner.impl;

import com.microservice.demo.twitter.fafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservice.demo.twitter.fafka.service.listener.TwitterKafkaStatusListener;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component
public class TwitterKafkaStreamRunner implements StreamRunner {
    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);
    private TwitterStream twitterStream;
    private final TwitterKafkaStatusListener twitterKafkaStatusListener;

    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData configData, TwitterKafkaStatusListener statusListener) {
        this.twitterToKafkaServiceConfigData = configData;
        this.twitterKafkaStatusListener = statusListener;
    }


    @Override
    public void start() throws TwitterException {
        twitterStream = (TwitterStream) new TwitterFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();

    }

    @PreDestroy
    private void shutDown() throws TwitterException {
        if (twitterStream != null) {
            LOG.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started filtering twitter stream for keywords {}", Arrays.toString(keywords));
    }
}
