package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PostUpdateRequest {
    private String title;
    private int price;
    private String content;
    public PostUpdateRequest(Post post) {
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.content = post.getContent();
    }
}