package com.example.demo.dto;

import lombok.Data;

@Data
public class PostUpdateResponse {
    private int id;
    public PostUpdateResponse(int id) {
        this.id = id;
    }
}