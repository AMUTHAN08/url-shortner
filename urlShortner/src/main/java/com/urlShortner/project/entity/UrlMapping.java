package com.urlShortner.project.entity;


import jakarta.persistence.*;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="urlMapping")
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String shortCode;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String originalUrl;

    private LocalDateTime createdDate=LocalDateTime.now();

    public UrlMapping(Long id, String shortCode, String originalUrl, LocalDateTime createdDate) {
        this.id = id;
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdDate = createdDate;
    }

    public  UrlMapping(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}

