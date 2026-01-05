package com.algaworks.algaposts.post.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostInput {
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @NotBlank
    private String author;
}
