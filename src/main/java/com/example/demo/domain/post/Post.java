package com.example.demo.domain.post;

import com.example.demo.dto.PostUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private PostType type;

    //private User user;

    private String title;
    private String content;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String ThumbnailUrl;

    public Post(PostType type, String title, String content, int price) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.price = price;
        this.ThumbnailUrl = "";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    protected Post() {

    }

    public void update(PostUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.price = request.getPrice();
        this.updatedAt = LocalDateTime.now();
    }
}