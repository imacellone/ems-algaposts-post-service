package com.algaworks.algaposts.post.domain.service;

import com.algaworks.algaposts.post.domain.exception.PostNotFoundException;
import com.algaworks.algaposts.post.domain.model.Post;
import com.algaworks.algaposts.post.domain.persistence.PostRepository;
import com.algaworks.algaposts.post.integration.messaging.publisher.PostEventPublisher;
import com.fasterxml.uuid.Generators;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostEventPublisher postEventPublisher;

    public Optional<Post> find(final UUID postId) {
        return postRepository.findById(postId);
    }

    @Transactional
    public Post create(@Valid Post postInput) {
        final Post post = Post.builder()
                .id(Generators.timeBasedEpochRandomGenerator().generate())
                .title(postInput.getTitle())
                .author(postInput.getAuthor())
                .body(postInput.getBody())
                .build();
        postEventPublisher.publishMessage(post);
        return save(post);
    }

    private Post save(final Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post patchPost(final UUID postId, final Post post) {
        if (postId == null || post == null) {
            throw new IllegalArgumentException();
        }
        final Post persistedPost = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        persistedPost.setAuthor(requireNonBlankElse(post.getAuthor(), persistedPost.getAuthor()));
        persistedPost.setBody(requireNonBlankElse(post.getBody(), persistedPost.getBody()));
        persistedPost.setTitle(requireNonBlankElse(post.getTitle(), persistedPost.getTitle()));
        persistedPost.setWordCount(Objects.requireNonNullElse(post.getWordCount(), persistedPost.getWordCount()));
        persistedPost.setCalculatedValue(Objects.requireNonNullElse(post.getCalculatedValue(), persistedPost.getCalculatedValue()));
        return persistedPost;
    }

    private static String requireNonBlankElse(final String string, final String fallback) {
        if (StringUtils.hasText(string)) {
            return string;
        }
        return fallback;
    }

}
