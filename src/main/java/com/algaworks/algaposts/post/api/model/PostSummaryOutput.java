package com.algaworks.algaposts.post.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostSummaryOutput {
    private final String id;
    private final String title;
    private final String summary;
    private final String author;
}
