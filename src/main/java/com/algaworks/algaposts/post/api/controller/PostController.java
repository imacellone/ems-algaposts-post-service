package com.algaworks.algaposts.post.api.controller;

import com.algaworks.algaposts.post.api.model.PostInput;
import com.algaworks.algaposts.post.api.model.PostOutput;
import com.algaworks.algaposts.post.domain.model.Post;
import com.algaworks.algaposts.post.domain.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostOutput create(@RequestBody @Valid final PostInput postInput) {
        final Post post = toPost(postInput);
        final Post created = postService.create(post);
        return toPostOutput(created);
    }

    @GetMapping("{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostOutput find(@PathVariable final UUID postId) {
        return postService.find(postId)
                .map(PostController::toPostOutput)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void findPaginated() {
        /*
            todo
                - Retorna uma lista paginada de PostSummaryOutput.
                - Suporta parâmetros page e size.
                - Retorna 200 OK com a estrutura:
                {
                  "page": 0,
                  "size": 10,
                  "totalElements": 45,
                  "totalPages": 5,
                  "content": [  lista de PostSummaryOutput  ]
                }
        */
    }

    private static Post toPost(final PostInput input) {
        return Post.builder()
                .title(input.getTitle())
                .body(input.getBody())
                .author(input.getAuthor())
                .build();
    }

    private static PostOutput toPostOutput(final Post post) {
        return PostOutput.builder()
                .id(post.getId())
                .author(post.getAuthor())
                .title(post.getTitle())
                .body(post.getBody())
                .calculatedValue(post.getCalculatedValue())
                .wordCount(post.getWordCount())
                .build();
    }

}
