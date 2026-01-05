package com.algaworks.algaposts.post.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostOutput {
    private final String id;
    private final String title;
    private final String body;
    private final String author;
    private final Long wordCount;
    private final Double calculatedValue;

    public static class PostOutputBuilder {
        public PostOutputBuilder id(UUID id) {
            this.id = id == null ? null : id.toString();
            return this;
        }
    }
}

