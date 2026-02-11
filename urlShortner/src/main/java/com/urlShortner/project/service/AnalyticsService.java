package com.urlShortner.project.service;

import com.urlShortner.project.kafka.ClickEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    @Autowired
    private final StringRedisTemplate redisTemplate;

    public AnalyticsService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void  recordClick(ClickEvent event){

        String code =event.getShortCode();
        String userIp=event.getUserIp();

        redisTemplate.opsForValue().increment("clicks:"+code);

        redisTemplate.opsForSet().add("users:" +code,userIp);

    }
}
