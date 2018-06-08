package com.shaffer.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new CaffeineCache("object", Caffeine.newBuilder()
                        .expireAfterAccess(10, TimeUnit.SECONDS)
                        .maximumSize(1000)
                        .removalListener((o, o2, removalCause) -> logger.info("Key {} was removed due ({})", o, removalCause))
                        .build()),
                new CaffeineCache("objects", Caffeine.newBuilder()
                        .expireAfterAccess(60, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .removalListener((o, o2, removalCause) -> logger.info("Key {} was removed due ({})", o, removalCause))
                        .build())
        ));

        return cacheManager;
    }

    //    @Bean
//    public CacheFactory cacheFactory() {
//
//        CacheFactory cacheFactory = new CacheFactory();
//        cacheFactory.setCacheName("defaultCache");
//        cacheFactory.setCacheClientFactory(new MemcacheClientFactoryImpl());
//        cacheFactory.setAddressProvider(new DefaultAddressProvider("localhost:11211"));
//        cacheFactory.setConfiguration(cacheConfiguration());
//
//        return cacheFactory;
//    }
//
//    @Bean
//    public CacheManager cacheManager() {
//
//        SSMCache ssmCache = new SSMCache(cache, 300, false);
//        SSMCacheManager ssmCacheManager = new SSMCacheManager();
//        ssmCacheManager.setCaches(Collections.singleton());
//
//        return ssmCacheManager;
//    }
//
//    private CacheConfiguration cacheConfiguration() {
//
//        ElastiCacheConfiguration elastiCacheConfiguration = new ElastiCacheConfiguration();
//        elastiCacheConfiguration.setConsistentHashing(true);
//        elastiCacheConfiguration.setClientMode(ClientMode.Static); // change this to ClientMode.Dynamic if you are using ElastiCache cluster
//
//        return elastiCacheConfiguration;
//    }
}
