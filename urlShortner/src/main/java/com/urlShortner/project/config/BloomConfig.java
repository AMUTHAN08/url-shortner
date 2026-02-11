package com.urlShortner.project.config;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class BloomConfig {

    @Bean
    public BloomFilter<String> urlBloomFilter() {
        return BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                1_000_000,   // expected URLs
                0.01         // 1% false positive rate
        );
    }
}
