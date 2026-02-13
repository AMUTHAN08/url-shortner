package com.urlShortner.project.config;

import com.google.common.hash.BloomFilter;
import com.urlShortner.project.Repository.UrlRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BloomAsyncLoader {

    private final UrlRepository urlRepository;
    private final BloomFilter<String> bloomFilter;

    public BloomAsyncLoader(UrlRepository urlRepository,
                            BloomFilter<String> bloomFilter) {
        this.urlRepository = urlRepository;
        this.bloomFilter = bloomFilter;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadAsync() {
        CompletableFuture.runAsync(() -> {
            System.out.println("Loading Bloom filter async...");
            urlRepository.findAll()
                    .forEach(url -> bloomFilter.put(url.getShortCode()));
            System.out.println("Bloom filter async load completed");
        });
    }
}
