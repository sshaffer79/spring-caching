package com.shaffer.configuration;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.Session;

@Configuration
@EnableJms
public class SQSConfiguration {
    @Autowired
    protected AWSCredentialsProvider credentialProvider;

    @Bean
    public AmazonSQS defaultAmazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_1)
                .withCredentials(credentialProvider).build();
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(AmazonSQS amazonSQS) {
        return new SQSConnectionFactory(
                new ProviderConfiguration(), amazonSQS);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(SQSConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setMaxMessagesPerTask(-1);
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1-3");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setCacheLevel(DefaultMessageListenerContainer.CACHE_AUTO);
        return factory;
    }
}
