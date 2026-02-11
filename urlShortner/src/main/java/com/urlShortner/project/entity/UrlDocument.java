//package com.urlShortner.project.entity;
//
//
//import jakarta.persistence.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//
//@Document(indexName = "url_mapping")
//public class UrlDocument {
//
//    @Id
//    private String id;
//
//    private String originalUrl;
//
//    private  String shortCode;
//
//    public UrlDocument(String id, String originalUrl, String shortCode) {
//        this.id = id;
//        this.originalUrl = originalUrl;
//        this.shortCode = shortCode;
//    }
//
//    public UrlDocument() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getOriginalUrl() {
//        return originalUrl;
//    }
//
//    public void setOriginalUrl(String originalUrl) {
//        this.originalUrl = originalUrl;
//    }
//
//    public String getShortCode() {
//        return shortCode;
//    }
//
//    public void setShortCode(String shortCode) {
//        this.shortCode = shortCode;
//    }
//}
