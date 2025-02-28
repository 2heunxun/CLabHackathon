package com.example.demo.dto;

import com.example.demo.domain.post.PostType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequestDTO {
    private PostType type;
    private String authorId;
    private String title;
    private String content;
    private MultipartFile thumbnailImage;
    private int price;
}