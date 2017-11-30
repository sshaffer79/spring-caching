package com.shaffer.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new CaffeineCache("object", Caffeine.newBuilder()
                        .expireAfterAccess(10, TimeUnit.SECONDS)
                        .maximumSize(1000)
                        .build()),
                new CaffeineCache("objects", Caffeine.newBuilder()
                        .expireAfterAccess(60, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .build())
        ));

        return cacheManager;
    }
}
