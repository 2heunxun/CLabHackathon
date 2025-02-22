package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import lombok.Data;

@Data
public class SimplePostDTO {
    private String title;
    private int price;

    public SimplePostDTO(Post post) {
        this.title = post.getTitle();
        this.price = post.getPrice();
    }
}