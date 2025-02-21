package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    @Enumerated(EnumType.STRING)
    private PostType type;

    //private String userName;

    private String title;
    private String content;
    private int price;
    private LocalDateTime createdAt;
    private String ThumbnailUrl;

    public PostDTO(Post post) {
        this.type = post.getType();
        //this.userName = post.getUser().getName();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.createdAt = post.getCreatedAt();
        this.ThumbnailUrl = post.getThumbnailUrl();
    }
}