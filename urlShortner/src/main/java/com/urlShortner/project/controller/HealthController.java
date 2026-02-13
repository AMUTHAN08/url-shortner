package com.urlShortner.project.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("UP");
    }


    @PostConstruct
    public void printPort() {
        System.out.println("APP STARTED ON PORT: " + System.getenv("PORT"));
    }
}