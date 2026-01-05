package com.algaworks.algaposts.post.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private UUID id;
    @NotBlank
    private String title;
    @NotBlank
    private String body;
    @NotBlank
    private String author;
    private Long wordCount;
    private Double calculatedValue;
}
