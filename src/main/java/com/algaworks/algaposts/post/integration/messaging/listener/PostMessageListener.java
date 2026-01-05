package com.algaworks.algaposts.post.integration.messaging.listener;

import com.algaworks.algaposts.post.domain.model.Post;
import com.algaworks.algaposts.post.domain.service.PostService;
import com.algaworks.algaposts.post.integration.messaging.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMessageListener {

    private final PostService postService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME_POST_PROCESSING, concurrency = "2-3")
    public void onPostProcessed(@Payload final PostProcessedMessage message) {
        final Post post = Post.builder()
                .wordCount(message.getWordCount())
                .calculatedValue(message.getCalculatedValue())
                .build();
        postService.patchPost(message.getPostId(), post);

    }

}
