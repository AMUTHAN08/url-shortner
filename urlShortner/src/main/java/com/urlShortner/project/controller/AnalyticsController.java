package com.urlShortner.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/v1/analytics")
public class AnalyticsController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    @GetMapping("/favicon.ico")
    @ResponseBody
    public void noFavicon() {}

    @GetMapping("{code}")
    public Map<String,Object> getStats(@PathVariable String code){
        String total=stringRedisTemplate.opsForValue().get("clicks:"+code);
        Long unique=stringRedisTemplate.opsForSet().size("users:"+code);

        Map<String,Object>result= new HashMap<>();
        result.put("totalClicks",total==null ?0:Integer.parseInt(total));
        result.put("uniqueUsers",unique==null?0:unique);

        return  result;
    }
}
