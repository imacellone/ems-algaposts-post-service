package com.algaworks.algaposts.post.integration.messaging.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfig {

    private static final String NAME_POST_PROCESSED = "posts.processed.v1.post-service";
    private static final String DLQ_NAME_POST_PROCESSED = NAME_POST_PROCESSED + ".dlq";
    public static final String QUEUE_NAME_POST_PROCESSED = NAME_POST_PROCESSED + ".q";
    private static final String FANOUT_NAME_POSTS_PROCESSED = "posts.processed.v1.e";

    @Bean
    Queue queue() {
        return QueueBuilder
                .durable(QUEUE_NAME_POST_PROCESSED)
                .withArguments(Map.of(
                        "x-dead-letter-exchange", "",
                        "x-dead-letter-routing-key", DLQ_NAME_POST_PROCESSED
                ))
                .build();
    }

    @Bean
    Queue dlqPostProcessed() {
        return QueueBuilder.durable(DLQ_NAME_POST_PROCESSED).build();
    }

    @Bean
    FanoutExchange postProcessedFanoutExchange() {
        return ExchangeBuilder.fanoutExchange(FANOUT_NAME_POSTS_PROCESSED).durable(true).build();
    }

    @Bean
    Binding postProcessedBinding() {
        return BindingBuilder.bind(queue()).to(postProcessedFanoutExchange());
    }

    @Bean
    JacksonJsonMessageConverter jacksonJsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
