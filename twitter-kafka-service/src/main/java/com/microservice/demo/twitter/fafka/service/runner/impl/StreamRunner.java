package com.microservice.demo.twitter.fafka.service.runner.impl;

import twitter4j.TwitterException;

public interface StreamRunner {
    void start() throws TwitterException;
}
