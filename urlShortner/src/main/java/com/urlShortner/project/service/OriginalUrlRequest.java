//package com.urlShortner.project.service;
//
//import com.urlShortner.project.Repository.UrlRepository;
//import com.urlShortner.project.entity.UrlMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OriginalUrlRequest {
//    private String originalRequest;
//
//    @Autowired
//    private UrlRepository urlRepository;
//
//    @Cacheable(value = "urlCache", key = "#code")
//    public String getOriginalRequest(String code){
//        System.out.println("Fetching from DB...");
//        UrlMapping urlMapping= urlRepository.findByShortCode(code).orElseThrow(() -> new RuntimeException("URL not found"));
//        return urlMapping.getOriginalUrl();
//    }
//
//}
