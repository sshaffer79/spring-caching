package com.shaffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties
public class CachingApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CachingApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CachingApplication.class);
    }
}
