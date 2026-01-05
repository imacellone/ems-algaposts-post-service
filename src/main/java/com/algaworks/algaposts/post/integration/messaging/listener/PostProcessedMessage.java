package com.algaworks.algaposts.post.integration.messaging.listener;

import lombok.Data;

import java.util.UUID;

@Data
public class PostProcessedMessage {
    private UUID postId;
    private Long wordCount;
    private Double calculatedValue;
}
