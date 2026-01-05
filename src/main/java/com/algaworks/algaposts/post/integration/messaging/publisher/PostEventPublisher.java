package com.algaworks.algaposts.post.integration.messaging.publisher;

import com.algaworks.algaposts.post.domain.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostEventPublisher {

    public void publishMessage(final Post post) {
        final PostCreatedMessage postData = PostCreatedMessage.builder()
                .postId(post.getId())
                .postBody(post.getBody())
                .build();
        // todo make it send to the exchange
    }

}
