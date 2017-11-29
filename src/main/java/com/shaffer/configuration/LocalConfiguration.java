package com.shaffer.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalConfiguration {
    @Bean
    public AWSCredentialsProvider credentialProvider() {
        return new ProfileCredentialsProvider();
    }
}
