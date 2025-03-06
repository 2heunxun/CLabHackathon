package com.example.demo.dto;

import com.example.demo.domain.post.Post;
import lombok.Data;

@Data
public class SimplePostDTO {
    private Long id;
    private String title;
    private int price;
    private String thumbnailUrl;

    public SimplePostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.price = post.getPrice();
        this.thumbnailUrl = post.getThumbnailUrl();
    }
}