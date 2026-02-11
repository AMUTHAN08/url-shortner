package com.urlShortner.project.controller;


import com.urlShortner.project.Repository.ElasticSearchUrlRepository;
import com.urlShortner.project.dto.UrlRequest;
import com.urlShortner.project.entity.UrlDocument;
//import com.urlShortner.project.service.OriginalUrlRequest;
import com.urlShortner.project.service.ShorterConvert;
import com.urlShortner.project.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

//@CrossOrigin(origins = "*")
@RestController
//@RequestMapping("/rest/v1")
public class UrlShortnerController {

    @Autowired
   private UrlService urlService;
//
//    @Autowired
//    private OriginalUrlRequest originalUrlRequest;


    @Autowired
    private ElasticSearchUrlRepository elasticSearchUrlRepository;


    @GetMapping("/favicon.ico")
    @ResponseBody
    public void noFavicon() {}

    @PostMapping("/rest/v1/shortner")
    public ResponseEntity<String> shortner(@RequestBody UrlRequest urlRequest){
        String code=urlService.shortnerCode(urlRequest.getOriginalUrl());
        return ResponseEntity.ok("http://localhost:8081/"+code);
    }

    @GetMapping("/{code:[a-zA-Z0-9]{6,10}}")
    public ResponseEntity<Void> redirect(@PathVariable String code, HttpServletRequest request){
        String originalUrl=urlService.getOriginalUrl(code,request);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(originalUrl)).build();
    }

    @GetMapping("/search")
    public List<UrlDocument> search(@RequestParam String keyword){
        return elasticSearchUrlRepository.findByOriginalUrlContaining(keyword);
    }

}
