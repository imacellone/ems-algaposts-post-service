package com.algaworks.algaposts.post.integration.messaging.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME_POST_PROCESSING = "text-processor-service.post-processing.v1.q";
}
