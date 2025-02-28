package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostUpdateRequest {
    private String title;
    private int price;
    private String content;
    private MultipartFile image;
}