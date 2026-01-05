package com.algaworks.algaposts.post.integration.messaging.publisher;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostCreatedMessage {
    private UUID postId;
    private String postBody;
}
