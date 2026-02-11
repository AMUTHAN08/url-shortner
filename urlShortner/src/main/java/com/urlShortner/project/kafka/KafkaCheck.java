package com.urlShortner.project.kafka;


import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class KafkaCheck {

    @Autowired
    Environment env;

    @PostConstruct
    public void check() {
        System.out.println("Kafka servers: " + env.getProperty("spring.kafka.bootstrap-servers"));
    }
}
