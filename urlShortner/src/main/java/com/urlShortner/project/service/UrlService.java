package com.urlShortner.project.service;

import com.google.common.hash.BloomFilter;
import com.urlShortner.project.Repository.UrlRepository;
//import com.urlShortner.project.entity.UrlDocument;
import com.urlShortner.project.entity.UrlMapping;
import com.urlShortner.project.kafka.ClickEvent;
import com.urlShortner.project.kafka.ClickProducer;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.swing.event.CaretListener;
import java.util.List;

@Service
public class UrlService {


    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private ShorterConvert shorterConvert;

//   @Autowired
//    private ElasticSearchUrlRepository elasticSearchUrlRepository;

    @Autowired
    private BloomFilter<String> bloomFilter;

    @Autowired
    private ClickProducer clickProducer;



    @Transactional
    public String shortnerCode(String originalUrl){
        UrlMapping mapping=new UrlMapping();

//        UrlDocument urlDocument=new UrlDocument();

        String code=shorterConvert.generate();

        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(code);

        urlRepository.save(mapping);

//        urlDocument.setId(mapping.getId().toString());
//        urlDocument.setOriginalUrl(originalUrl);
//        urlDocument.setShortCode(code);

        bloomFilter.put(code);

        //elasticSearchUrlRepository.save(urlDocument);

        return code;

    }

   // @Cacheable(value = "urlCache", key = "#code")
    public String getOriginalUrl(String code, HttpServletRequest request) {

        if ("favicon.ico".equals(code)) {
            return "";
        }
        UrlMapping urlMapping = getUrlFromCacheOrDb(code);

        System.out.println("Bloom check running for: " + code);

        ClickEvent clickEvent = new ClickEvent();
        clickEvent.setShortCode(code);
        clickEvent.setUserIp(request.getRemoteAddr());
        System.out.println(clickEvent.getUserIp());
        clickEvent.setTimestamp(System.currentTimeMillis());

        System.out.println("Fetching from DB...");
        clickProducer.sendClickEvent(clickEvent);
        return urlMapping.getOriginalUrl();
    }



    @Cacheable(value = "urlCache", key = "#code")
    public UrlMapping getUrlFromCacheOrDb(String code) {

        if (!bloomFilter.mightContain(code)) {
            throw new RuntimeException("URL not found");
        }

        return urlRepository.findByShortCode(code)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }
    @PostConstruct
    public void loadBloomFromDb() {
        List<UrlMapping> all = urlRepository.findAll();
        for (UrlMapping url : all) {
            bloomFilter.put(url.getShortCode());
        }
        System.out.println("Bloom filter loaded with existing URLs");
    }
}
