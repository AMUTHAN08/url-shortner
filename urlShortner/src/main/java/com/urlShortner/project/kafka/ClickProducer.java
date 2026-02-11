package com.urlShortner.project.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClickProducer {

    private final KafkaTemplate<String,ClickEvent> kafkaTemplate;

    public ClickProducer(KafkaTemplate<String, ClickEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendClickEvent(ClickEvent clickEvent){

        kafkaTemplate.send("click_stream_topic",clickEvent.getShortCode(),clickEvent);
    }


}
