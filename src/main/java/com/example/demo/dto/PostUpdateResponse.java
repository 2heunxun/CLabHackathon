package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostUpdateResponse {
    private int id;
    private String title;
    private String content;
    private int price;
    private LocalDateTime updatedDateTime;

    public PostUpdateResponse(Post post) {
        this.id = id;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.updatedDateTime = LocalDateTime.now();
    }
}