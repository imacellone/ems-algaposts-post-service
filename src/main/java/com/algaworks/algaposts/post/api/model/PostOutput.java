package com.algaworks.algaposts.post.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostOutput {
    private final String id;
    private final String title;
    private final String body;
    private final String author;
    private final Long wordCount;
    private final Double calculatedValue;
}
