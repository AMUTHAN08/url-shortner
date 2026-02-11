package com.urlShortner.project.kafka;

import com.urlShortner.project.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClickConsumer {


    @Autowired
    private AnalyticsService analyticsService;

    @KafkaListener(topics="click_stream_topic",groupId = "url-analytics-group")
    public void consumerClickEvent(ClickEvent clickEvent){
        analyticsService.recordClick(clickEvent);
        System.out.println("Receiving click for :" + clickEvent.getShortCode());
    }

}
