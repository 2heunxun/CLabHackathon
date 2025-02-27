package com.example.demo.dto;

import com.example.demo.domain.post.PostType;
import lombok.Data;

@Data
public class PostRequestDTO {
    private PostType type;
    private String authorId;
    private String title;
    private String content;
    private int price;
}