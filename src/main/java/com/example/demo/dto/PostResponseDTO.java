package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDTO {
    @Enumerated(EnumType.STRING)
    private PostType type;

    private String userName;

    private String title;
    private String content;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnailUrl;

    public PostResponseDTO(Post post) {
        this.type = post.getType();
        this.userName = post.getAuthor().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.thumbnailUrl = post.getThumbnailUrl();
    }
}